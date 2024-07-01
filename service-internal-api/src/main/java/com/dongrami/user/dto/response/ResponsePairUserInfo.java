package com.dongrami.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ResponsePairUserInfo(
        @Schema(description = "짝꿍 아이디")
        String pairNickname,

        @Schema(description = "짝꿍 프로필 이미지 URL")
        String pairProfileImageUrl,

        @Schema(description = "짝꿍 생성 일시")
        LocalDateTime pairCreateDateTime
) {
}
