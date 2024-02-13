package com.dongrami.todo.dto.response;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseTodoDto {
    private Long id;
    private String title;
    private String content;
    private TodoStatus todoStatus;
    private LocalDateTime alarmDateTime;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @Builder
    public ResponseTodoDto(Long id, String title, String content, TodoStatus todoStatus, LocalDateTime alarmDateTime, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.todoStatus = todoStatus;
        this.alarmDateTime = alarmDateTime;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public static ResponseTodoDto from(TodoEntity todoEntity) {
        return ResponseTodoDto.builder()
                .id(todoEntity.getId())
                .title(todoEntity.getTitle())
                .content(todoEntity.getContent())
                .todoStatus(todoEntity.getTodoStatus())
                .createdDateTime(todoEntity.getCreatedDateTime())
                .updatedDateTime(todoEntity.getUpdatedDateTime())
                .build();
    }
}
