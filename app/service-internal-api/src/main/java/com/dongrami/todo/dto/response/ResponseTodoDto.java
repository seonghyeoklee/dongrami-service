package com.dongrami.todo.dto.response;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import com.dongrami.user.dto.response.ResponseUserInfoDto;
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
    private LocalDateTime notificationDateTime;
    private Boolean isPinned;
    private LocalDateTime pinnedDateTime;
    private ResponseUserInfoDto user;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static ResponseTodoDto from(TodoEntity todoEntity) {
        return ResponseTodoDto.builder()
                .id(todoEntity.getId())
                .content(todoEntity.getContent())
                .memo(todoEntity.getMemo())
                .todoStatus(todoEntity.getTodoStatus())
                .notificationDateTime(todoEntity.getNotificationDateTime())
                .isPinned(todoEntity.isPinned())
                .pinnedDateTime(todoEntity.getPinnedDateTime())
                .user(ResponseUserInfoDto.from(todoEntity.getUserEntity()))
                .createdDateTime(todoEntity.getCreatedDateTime())
                .updatedDateTime(todoEntity.getUpdatedDateTime())
                .build();
    }
}
