package com.dongrami.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseRefreshToken(
        @Schema(description = "accessToken")
        String accessToken,

        @Schema(description = "refreshToken")
        String refreshToken
) {
    public static ResponseRefreshToken from(String accessToken, String refreshToken) {
        return new ResponseRefreshToken(accessToken, refreshToken);
    }
}
