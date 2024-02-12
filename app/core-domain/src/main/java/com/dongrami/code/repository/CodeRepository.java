package com.dongrami.code.repository;

import com.dongrami.code.domain.CodeGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<CodeGroupEntity, Long> {
}
