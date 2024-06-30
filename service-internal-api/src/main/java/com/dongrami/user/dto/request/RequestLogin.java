package com.dongrami.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record RequestLogin(
        @Schema(description = "아이디", example = "dongrami")
        @NotBlank(message = "아이디를 입력해주세요.")
        String id,

        @Schema(description = "비밀번호", example = "password")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password
) {
}
