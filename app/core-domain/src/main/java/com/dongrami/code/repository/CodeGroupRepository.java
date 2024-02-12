package com.dongrami.code.repository;

import com.dongrami.code.domain.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeGroupRepository extends JpaRepository<CodeEntity, Long> {
}
