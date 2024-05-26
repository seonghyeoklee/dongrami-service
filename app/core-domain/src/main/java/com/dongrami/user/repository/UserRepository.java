package com.dongrami.user.repository;

import com.dongrami.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserUniqueId(String userId);

    Optional<UserEntity> findUserByUserUniqueId(String userId);

}
