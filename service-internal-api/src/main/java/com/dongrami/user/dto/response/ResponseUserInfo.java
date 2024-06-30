package com.dongrami.user.dto.response;

import com.dongrami.user.domain.ProviderType;
import com.dongrami.user.domain.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseUserInfo(
        @Schema(description = "PK")
        Long id,

        @Schema(description = "이메일")
        String email,

        @Schema(description = "제공자 타입")
        ProviderType providerType,

        @Schema(description = "유저 이름")
        String username,

        @Schema(description = "유저 개인 색상")
        String userPersonalColor,

        @Schema(description = "전화번호")
        String phoneNumber,

        @Schema(description = "닉네임")
        String nickname
) {
    public static ResponseUserInfo of(UserEntity entity) {
        return new ResponseUserInfo(
                entity.getId(),
                entity.getEmail(),
                entity.getProviderType(),
                entity.getUsername(),
                entity.getProfileInfo().getUserPersonalColor().getColor(),
                entity.getPhoneNumber(),
                entity.getProfileInfo().getNickname()
        );
    }
}
