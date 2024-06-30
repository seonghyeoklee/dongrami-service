package com.dongrami.user.dto.request;

import com.dongrami.user.domain.Location;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestUpdateProfileInfo(
        @Schema(description = "닉네임", example = "동글한라미")
        @NotBlank(message = "닉네임을 입력해주세요.")
        String nickname,

        @Schema(description = "지역", example = "SEOUL")
        @NotNull(message = "지역을 선택해주세요.")
        Location location
) {
}
