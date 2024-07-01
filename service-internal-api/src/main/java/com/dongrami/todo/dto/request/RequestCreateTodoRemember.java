package com.dongrami.todo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record RequestCreateTodoRemember(
        @Schema(description = "Todo PK", example = "1")
        @NotNull(message = "Todo ID는 필수값입니다.")
        Long todoId
) {
}
