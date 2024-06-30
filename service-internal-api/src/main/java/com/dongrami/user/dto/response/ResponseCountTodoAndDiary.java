package com.dongrami.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseCountTodoAndDiary(
        @Schema(description = "할 일 개수", example = "3")
        long todoCount,

        @Schema(description = "일기 개수", example = "5")
        long diaryCount
) {
}
