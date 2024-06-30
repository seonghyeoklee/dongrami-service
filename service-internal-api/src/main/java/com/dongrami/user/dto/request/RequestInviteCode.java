package com.dongrami.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record RequestInviteCode(
        @Schema(description = "초대 코드", example = "산뜻한겨울#0017")
        @NotBlank(message = "초대 코드를 입력해주세요.")
        String inviteCode
) {
}
