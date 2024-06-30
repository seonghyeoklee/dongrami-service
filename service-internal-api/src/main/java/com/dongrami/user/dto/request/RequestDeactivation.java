package com.dongrami.user.dto.request;

import com.dongrami.user.domain.UserDeactivationReason;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record RequestDeactivation(
        @Schema(description = "탈퇴 사유", example = "INFREQUENT_USE")
        @NotNull(message = "탈퇴 사유를 선택해주세요.")
        UserDeactivationReason userDeactivationReason,

        @Schema(description = "사유 상세 (선택)")
        String deactivationDetailOpinion
) {
}
