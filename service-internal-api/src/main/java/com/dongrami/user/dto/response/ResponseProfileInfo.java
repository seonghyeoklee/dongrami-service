package com.dongrami.user.dto.response;

import com.dongrami.user.domain.Location;
import com.dongrami.user.domain.ProviderType;
import com.dongrami.user.domain.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseProfileInfo(
        @Schema(description = "유저 아이디")
        Long id,

        @Schema(description = "닉네임")
        String nickname,

        @Schema(description = "이메일")
        String email,

        @Schema(description = "제공자 타입")
        ProviderType providerType,

        @Schema(description = "전화번호")
        String phoneNumber,

        @Schema(description = "위치")
        Location location,

        @Schema(description = "생리주기 여부")
        boolean isMenstrualCycle
) {
    public static ResponseProfileInfo from(UserEntity entity) {
        return new ResponseProfileInfo(
                entity.getId(),
                entity.getProfileInfo().getNickname(),
                entity.getEmail(),
                entity.getProviderType(),
                entity.getPhoneNumber(),
                entity.getProfileInfo().getLocation(),
                entity.getProfileInfo().isMenstrualCycle()
        );
    }
}
