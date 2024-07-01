package com.dongrami.todo.dto.request;

import com.dongrami.todo.domain.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record RequestUpdateTodo(
        @Schema(description = "할일 내용", example = "코딩")
        @NotBlank(message = "내용을 입력해주세요.")
        String content,

        @Schema(description = "할일 메모", example = "코딩하기")
        String memo,

        @Schema(description = "할일 상태", example = "TODO")
        TodoStatus todoStatus,

        @Schema(description = "할일 알림", example = "22:10:30")
        LocalDateTime notificationDateTime
) {
}
