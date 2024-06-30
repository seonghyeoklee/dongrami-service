package com.dongrami.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseInviteCode(
        @Schema(description = "초대 코드")
        String inviteCode
) {
}
