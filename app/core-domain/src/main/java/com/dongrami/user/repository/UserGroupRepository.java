package com.dongrami.user.repository;

import com.dongrami.user.domain.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long>{

    Optional<UserGroupEntity> findByGroupCode(String groupCode);

}
