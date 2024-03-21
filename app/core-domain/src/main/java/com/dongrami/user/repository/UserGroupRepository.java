package com.dongrami.user.repository;

import com.dongrami.user.domain.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long>{
}
