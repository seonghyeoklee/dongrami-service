package com.dongrami.user.repository;

import com.dongrami.user.domain.UserRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokenEntity, Long> {

    UserRefreshTokenEntity findByUserId(String userId);

    UserRefreshTokenEntity findByUserIdAndRefreshToken(String userId, String refreshToken);

}
