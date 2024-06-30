package com.dongrami.todo.dto.request;

import com.dongrami.todo.domain.TodoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestUpdateTodoDto {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "메모를 입력해주세요.")
    private String memo;

    @NotNull(message = "상태를 입력해주세요.")
    private TodoStatus todoStatus;

    @NotNull(message = "알람 일시를 입력해주세요.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime notificationDateTime;

}
