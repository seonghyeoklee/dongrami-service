package com.dongrami.user.dto;

import com.dongrami.user.domain.ProfileInfo;
import com.dongrami.user.domain.ProviderType;
import com.dongrami.user.domain.RoleType;
import com.dongrami.user.domain.UserEntity;

public record UserDto(
        Long id,
        String userUniqueId,
        String username,
        String email,
        boolean isEmailVerified,
        ProviderType providerType,
        RoleType roleType,
        String phoneNumber,
        ProfileInfo profileInfo
) {
    public static UserDto of(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getUserUniqueId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.isEmailVerified(),
                userEntity.getProviderType(),
                userEntity.getRoleType(),
                userEntity.getPhoneNumber(),
                userEntity.getProfileInfo()
        );
    }
}
