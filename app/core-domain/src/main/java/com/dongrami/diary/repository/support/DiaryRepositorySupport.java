package com.dongrami.diary.repository.support;

import com.dongrami.diary.domain.DiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryRepositorySupport {

    Page<DiaryEntity> findBySearch(Pageable pageable);

}
