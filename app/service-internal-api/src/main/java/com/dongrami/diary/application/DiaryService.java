package com.dongrami.diary.application;

import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.diary.dto.DiaryDto;
import com.dongrami.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public Page<DiaryDto> getDiaryPage(Pageable pageable) {
        Page<DiaryEntity> diaryEntityPage = diaryRepository.findBySearch(pageable);

        return diaryEntityPage.map(DiaryDto::from);
    }

}
