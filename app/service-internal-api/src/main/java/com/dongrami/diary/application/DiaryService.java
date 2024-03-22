package com.dongrami.diary.application;

import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.diary.dto.DiaryDto;
import com.dongrami.diary.dto.request.RequestCreateDiaryDto;
import com.dongrami.diary.dto.request.RequestUpdateDiaryDto;
import com.dongrami.diary.repository.DiaryRepository;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final UserService userService;
    private final DiaryRepository diaryRepository;

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
                request.isPublic()
        );

        diaryRepository.save(diaryEntity);
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

}
