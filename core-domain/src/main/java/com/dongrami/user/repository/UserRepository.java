package com.dongrami.user.repository;

import com.dongrami.user.domain.InviteCode;
import com.dongrami.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserUniqueId(String userId);

    Optional<UserEntity> findUserByUserUniqueId(String userId);

    @Query("SELECT u FROM UserEntity u WHERE u.profileInfo.inviteCode = :inviteCode")
    Optional<UserEntity> findByInviteCode(@Param("inviteCode") InviteCode inviteCode);
}
