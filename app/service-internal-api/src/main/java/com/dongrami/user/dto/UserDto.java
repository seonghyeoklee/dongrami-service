package com.dongrami.user.dto;

import com.dongrami.user.domain.ProviderType;
import com.dongrami.user.domain.RoleType;
import com.dongrami.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userUniqueId;
    private String username;
    private String password;
    private String email;
    private String emailVerifiedYn;
    private String profileImageUrl;
    private ProviderType providerType;
    private RoleType roleType;
    private boolean isBlocked;
    private String blockReason;
    private String color;

    public static UserDto from(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .userUniqueId(userEntity.getUserUniqueId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .emailVerifiedYn(userEntity.getEmailVerifiedYn())
                .profileImageUrl(userEntity.getProfileInfo().getProfileImageUrl())
                .providerType(userEntity.getProviderType())
                .roleType(userEntity.getRoleType())
                .color(userEntity.getProfileInfo().getUserPersonalColor().getColor())
                .build();
    }

}
