package com.dongrami.todo.dto.request;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestTodoDto {
    private Long id;
    private String title;
    private String content;
    private TodoStatus todoStatus;
    private LocalDateTime alarmDateTime;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .todoStatus(todoStatus)
                .alarmDateTime(alarmDateTime)
                .build();
    }
}
