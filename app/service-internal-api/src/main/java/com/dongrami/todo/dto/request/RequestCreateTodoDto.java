package com.dongrami.todo.dto.request;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestCreateTodoDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotNull(message = "상태를 입력해주세요.")
    private TodoStatus todoStatus;

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .title(title)
                .content(content)
                .todoStatus(todoStatus)
                .build();
    }
}
