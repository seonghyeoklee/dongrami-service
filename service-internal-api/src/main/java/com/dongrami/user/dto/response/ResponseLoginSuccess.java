package com.dongrami.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseLoginSuccess(
        @Schema(description = "accessToken")
        String accessToken,

        @Schema(description = "refreshToken")
        String refreshToken
) {
    public static ResponseLoginSuccess from(String accessToken, String refreshToken) {
        return new ResponseLoginSuccess(accessToken, refreshToken);
    }
}
