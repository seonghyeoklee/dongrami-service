package com.dongrami.todo.repository.support;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoSearchDto {
    private String title;
    private String content;
    private String todoStatus;
    private LocalDateTime alarmDateTime;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
