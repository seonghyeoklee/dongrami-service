package com.dongrami.todo.dto;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String content;
    private String memo;
    private TodoStatus todoStatus;
    private LocalDate todoDate;
    private LocalDateTime notificationDateTime;
    private boolean isPinned;
    private LocalDateTime pinnedDateTime;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static TodoDto from(TodoEntity todoEntity) {
        return TodoDto.builder()
                .id(todoEntity.getId())
                .content(todoEntity.getContent())
                .memo(todoEntity.getMemo())
                .todoStatus(todoEntity.getTodoStatus())
                .todoDate(todoEntity.getTodoDate())
                .notificationDateTime(todoEntity.getNotificationDateTime())
                .isPinned(todoEntity.isPinned())
                .pinnedDateTime(todoEntity.getPinnedDateTime())
                .createdDateTime(todoEntity.getCreatedDateTime())
                .updatedDateTime(todoEntity.getUpdatedDateTime())
                .build();
    }
}
