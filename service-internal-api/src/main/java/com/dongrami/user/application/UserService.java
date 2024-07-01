package com.dongrami.user.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.user.domain.InviteCode;
import com.dongrami.user.domain.UserDeactivationEntity;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.domain.UserNotificationSettingEntity;
import com.dongrami.user.dto.request.RequestDeactivation;
import com.dongrami.user.dto.request.RequestInviteCode;
import com.dongrami.user.dto.request.RequestUpdateNotification;
import com.dongrami.user.dto.request.RequestUpdateProfileInfo;
import com.dongrami.user.dto.response.ResponseCountTodoAndDiary;
import com.dongrami.user.dto.response.ResponsePairUserInfo;
import com.dongrami.user.dto.response.ResponseUserNotification;
import com.dongrami.user.repository.UserDeactivationRepository;
import com.dongrami.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDeactivationRepository userDeactivationRepository;

    @Transactional(readOnly = true)
    public UserEntity getUser(String userUniqueId) {
        return userRepository.findByUserUniqueId(userUniqueId);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByUserUniqueId(String userUniqueId) {
        return userRepository.findUserByUserUniqueId(userUniqueId)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_USER));
    }

    public void updateProfileInfo(String userUniqueId, RequestUpdateProfileInfo request) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        userEntity.updateProfileInfo(request.nickname(), request.location());
    }

    public void updateMenstrual(String userUniqueId) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        userEntity.updateMenstrual();
    }

    public void updateDeactivation(String userUniqueId, RequestDeactivation request) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        UserDeactivationEntity userDeactivationEntity = UserDeactivationEntity.builder()
                .userEntity(userEntity)
                .deactivationDate(LocalDateTime.now())
                .userDeactivationReason(request.userDeactivationReason())
                .deactivationDetailOpinion(request.deactivationDetailOpinion())
                .build();

        userDeactivationRepository.save(userDeactivationEntity);
    }

    public ResponseCountTodoAndDiary countTodoAndDiary(String userUniqueId) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        long todoCount = userEntity.getTodoEntities().stream()
                .filter(todoEntity -> !todoEntity.isDeleted())
                .count();

        long diaryCount = userEntity.getDiaryEntities().stream()
                .filter(diaryEntity -> !diaryEntity.isDeleted())
                .count();

        return new ResponseCountTodoAndDiary(todoCount, diaryCount);
    }

    public String getInviteCode(String userUniqueId) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        return userEntity.getInviteCode();
    }

    public void updateInviteCode(String userUniqueId, RequestInviteCode request) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        if (userEntity.getPairUserEntity() != null) {
            throw new BaseException(ErrorCode.PAIR_USER_ALREADY_EXIST);
        }

        UserEntity inviteUserEntity = userRepository.findByInviteCode(InviteCode.of(request.inviteCode()))
                .orElseThrow(() -> new BaseException(ErrorCode.INVITE_CODE_INVALID));

        userEntity.updatePairUserEntity(inviteUserEntity);
    }

    public List<ResponseUserNotification> getNotification(String userUniqueId) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        return userEntity.getUserNotificationSettingEntities().stream()
                .map(userNotificationSettingEntity ->
                        new ResponseUserNotification(
                                userNotificationSettingEntity.getNotificationType(),
                                userNotificationSettingEntity.isEnabled()
                        )
                ).toList();
    }

    public void updateNotification(String userUniqueId, RequestUpdateNotification request) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        UserNotificationSettingEntity userNotificationSettingEntity = userEntity.getUserNotificationSettingEntities().stream()
                .filter(entity -> request.notificationType().equals(entity.getNotificationType()))
                .findFirst()
                .orElseThrow(() -> new BaseException(ErrorCode.NOTIFICATION_SETTING_NOT_FOUND));

        userNotificationSettingEntity.updateNotificationSetting();
    }

    public ResponsePairUserInfo getPairUserInfo(String userUniqueId) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);
        UserEntity pairUserEntity = userEntity.getPairUserEntity();

        if (pairUserEntity == null) {
            throw new BaseException(ErrorCode.PAIR_USER_NOT_EXIST);
        }

        return new ResponsePairUserInfo(
                pairUserEntity.getProfileInfo().getNickname(),
                pairUserEntity.getProfileInfo().getProfileImageUrl(),
                userEntity.getPairUserSettingTime()
        );
    }

    public void deletePairUser(String userUniqueId) {
        UserEntity userEntity = getUserByUserUniqueId(userUniqueId);

        userEntity.deletePairUserEntity();
    }
}
