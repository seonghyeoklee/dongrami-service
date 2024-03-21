package com.dongrami.user.application;

import com.dongrami.common.key.KeyGenerator;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.domain.UserGroupEntity;
import com.dongrami.user.dto.UserGroupDto;
import com.dongrami.user.dto.request.RequestCreateUserGroupDto;
import com.dongrami.user.dto.request.RequestUpdateUserGroupDto;
import com.dongrami.user.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserGroupService {
    private final KeyGenerator keyGenerator;
    private final UserService userService;
    private final UserGroupRepository userGroupRepository;

    public void createUserGroup(String username, RequestCreateUserGroupDto request) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        validateAlreadyHasUserGroup(userEntity);

        UserGroupEntity userGroupEntity = UserGroupEntity.builder()
                .groupName(request.getGroupName())
                .groupDescription(request.getGroupDescription())
                .groupCode(keyGenerator.generateKey())
                .userEntity(userEntity)
                .build();

        userGroupRepository.save(userGroupEntity);
    }

    private void validateAlreadyHasUserGroup(UserEntity userEntity) {
        UserGroupEntity userGroupEntity = userEntity.getUserGroupEntity();
        if (userGroupEntity != null) {
            throw new BaseException(ErrorCode.USER_GROUP_ALREADY_EXIST);
        }
    }

    public UserGroupDto getUserGroup(String username) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        return UserGroupDto.from(
                userEntity.getUserGroupEntity()
        );
    }

    public void deleteUserGroup(String username) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        UserGroupEntity userGroupEntity = userEntity.getUserGroupEntity();
        if (userGroupEntity == null) {
            throw new BaseException(ErrorCode.USER_GROUP_NOT_EXIST);
        }

        if (!userGroupEntity.isOwner(userEntity)) {
            throw new BaseException(ErrorCode.USER_GROUP_OWNER_CANNOT_DELETE);
        }

        userGroupEntity.removeAllUser();

        userGroupRepository.delete(userGroupEntity);
    }

    public void joinUserGroup(String username, String groupCode) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        if (userEntity.hasUserGroup()) {
            throw new BaseException(ErrorCode.USER_GROUP_ALREADY_JOINED);
        }

        UserGroupEntity userGroupEntity = userGroupRepository.findByGroupCode(groupCode)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_GROUP_NOT_EXIST_BY_GROUP_CODE));

        userGroupEntity.addUserEntity(userEntity);
    }

    public void leaveUserGroup(String username) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        UserGroupEntity userGroupEntity = userEntity.getUserGroupEntity();
        if (userGroupEntity == null) {
            throw new BaseException(ErrorCode.USER_GROUP_NOT_EXIST);
        }

        userGroupEntity.removeUserEntity(userEntity);
    }

    public void updateUserGroup(String username, RequestUpdateUserGroupDto request) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        UserGroupEntity userGroupEntity = userEntity.getUserGroupEntity();
        if (userGroupEntity == null) {
            throw new BaseException(ErrorCode.USER_GROUP_NOT_EXIST);
        }

        if (!userGroupEntity.isOwner(userEntity)) {
            throw new BaseException(ErrorCode.USER_GROUP_OWNER_CANNOT_UPDATE);
        }

        userGroupEntity.updateGroupInfo(request.getGroupName(), request.getGroupDescription());
    }

}
