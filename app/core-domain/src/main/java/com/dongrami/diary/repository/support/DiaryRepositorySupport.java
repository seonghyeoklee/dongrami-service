package com.dongrami.diary.repository.support;

import com.dongrami.diary.domain.DiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface DiaryRepositorySupport {

    Page<DiaryEntity> findDiaryPageByCurrentDate(Long userId, Pageable pageable, LocalDate currentDate);

    Optional<DiaryEntity> findByUserIdAndDiaryId(Long userId, Long diaryId);

}
