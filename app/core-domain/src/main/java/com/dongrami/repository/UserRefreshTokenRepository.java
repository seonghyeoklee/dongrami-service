package com.dongrami.repository;

import com.dongrami.domain.UserRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokenEntity, Long> {

    UserRefreshTokenEntity findByUserId(String userId);

    UserRefreshTokenEntity findByUserIdAndRefreshToken(String userId, String refreshToken);

}
