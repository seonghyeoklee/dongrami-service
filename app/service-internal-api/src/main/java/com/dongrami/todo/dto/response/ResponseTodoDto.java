package com.dongrami.todo.dto.response;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ResponseTodoDto {
    private Long id;
    private String content;
    private String memo;
    private TodoStatus todoStatus;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static ResponseTodoDto from(TodoEntity todoEntity) {
        return ResponseTodoDto.builder()
                .id(todoEntity.getId())
                .content(todoEntity.getContent())
                .memo(todoEntity.getMemo())
                .todoStatus(todoEntity.getTodoStatus())
                .createdDateTime(todoEntity.getCreatedDateTime())
                .updatedDateTime(todoEntity.getUpdatedDateTime())
                .build();
    }
}
