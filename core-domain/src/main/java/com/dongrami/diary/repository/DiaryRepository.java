package com.dongrami.diary.repository;

import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.diary.repository.support.DiaryRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long>, DiaryRepositorySupport {
}
