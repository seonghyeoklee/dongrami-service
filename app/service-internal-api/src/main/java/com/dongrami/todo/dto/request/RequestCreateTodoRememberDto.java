package com.dongrami.todo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestCreateTodoRememberDto {

    @Schema(description = "Todo ID", example = "1")
    @NotNull(message = "Todo ID는 필수값입니다.")
    private Long todoId;

}
