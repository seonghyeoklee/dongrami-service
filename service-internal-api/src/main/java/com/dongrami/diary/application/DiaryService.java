package com.dongrami.diary.application;

import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.diary.dto.DiaryDto;
import com.dongrami.diary.dto.request.RequestCreateDiaryDto;
import com.dongrami.diary.dto.request.RequestUpdateDiaryDto;
import com.dongrami.diary.event.DiaryCalendarCreateEvent;
import com.dongrami.diary.repository.DiaryRepository;
import com.dongrami.event.Events;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.feeling.domain.FeelingEntity;
import com.dongrami.feeling.repository.FeelingRepository;
import com.dongrami.tag.dto.TagDto;
import com.dongrami.tag.entity.TagEntity;
import com.dongrami.tag.repository.TagRepository;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final UserService userService;
    private final DiaryRepository diaryRepository;
    private final FeelingRepository feelingRepository;
    private final TagRepository tagRepository;

    public Page<DiaryDto> getDiaryPage(String username, Pageable pageable, LocalDate currentDate) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        return diaryRepository.findDiaryPageByCurrentDate(userEntity.getId(), pageable, currentDate)
                .map(DiaryDto::from);
    }

    public void createDiary(String userUniqueId, RequestCreateDiaryDto request) {
        UserEntity userEntity = userService.getUserByUserUniqueId(userUniqueId);

        DiaryEntity diaryEntity = DiaryEntity.create(
                userEntity,
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );

        List<TagEntity> tagEntities = tagRepository.findAllById(request.getTags());
        if (request.getTags().size() != tagEntities.size()) {
            throw new BaseException(ErrorCode.TAG_NOT_EXIST);
        }

        diaryEntity.addDiaryTags(tagEntities);

        FeelingEntity feelingEntity = feelingRepository.findById(request.getFeelingId())
                .orElseThrow(() -> new BaseException(ErrorCode.FEELING_NOT_EXIST));

        diaryEntity.addDiaryFeeling(feelingEntity);

        DiaryEntity savedDiary = diaryRepository.save(diaryEntity);

        Events.publish(new DiaryCalendarCreateEvent(savedDiary.getId()));
    }

    public DiaryDto getDiaryById(String username, Long diaryId) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);
        
        DiaryEntity diaryEntity = diaryRepository.findByUserIdAndDiaryId(userEntity.getId(), diaryId)
                .orElseThrow(() -> new BaseException(ErrorCode.DIARY_NOT_EXIST));

        return DiaryDto.from(diaryEntity);
    }

    public void updateDiary(String username, Long id, RequestUpdateDiaryDto request) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        DiaryEntity diaryEntity = diaryRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.DIARY_NOT_EXIST));

        if (!diaryEntity.isOwner(userEntity)) {
            throw new BaseException(ErrorCode.DIARY_NOT_OWNER_CANNOT_UPDATE);
        }

        List<TagEntity> tagEntities = tagRepository.findAllById(request.getTags());
        if (request.getTags().size() != tagEntities.size()) {
            throw new BaseException(ErrorCode.TAG_NOT_EXIST);
        }

        diaryEntity.modifyDiaryTags(tagEntities);

        diaryEntity.update(
                request.getTitle(),
                request.getContent(),
                request.isPublic()
        );
    }

    public void deleteDiary(String username, Long id) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        DiaryEntity diaryEntity = diaryRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.DIARY_NOT_EXIST));

        if (!diaryEntity.isOwner(userEntity)) {
            throw new BaseException(ErrorCode.DIARY_NOT_OWNER_CANNOT_DELETE);
        }

        diaryEntity.delete();
    }

    public List<TagDto> getRecommendationTags(String username, String tagName) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        List<TagEntity> tagEntities = tagRepository.findByTagNameAndUserEntity(tagName, userEntity);

        return tagEntities.stream()
                .map(TagDto::from)
                .toList();
    }

}
