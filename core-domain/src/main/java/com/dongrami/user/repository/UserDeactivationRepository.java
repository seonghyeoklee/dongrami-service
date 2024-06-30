package com.dongrami.user.repository;

import com.dongrami.user.domain.UserDeactivationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeactivationRepository extends JpaRepository<UserDeactivationEntity, Long> {
}
