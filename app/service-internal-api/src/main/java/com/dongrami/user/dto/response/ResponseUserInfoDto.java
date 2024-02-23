package com.dongrami.user.dto.response;

import com.dongrami.user.domain.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class ResponseUserInfoDto {
    private String email;
    private String username;
    private String profileImageUrl;

    @Builder
    public ResponseUserInfoDto(String email, String username, String profileImageUrl) {
        this.email = email;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }

    public static ResponseUserInfoDto from(UserEntity entity) {
        return ResponseUserInfoDto.builder()
                .email(entity.getEmail())
                .username(entity.getUsername())
                .profileImageUrl(entity.getProfileImageUrl())
                .build();
    }
}
