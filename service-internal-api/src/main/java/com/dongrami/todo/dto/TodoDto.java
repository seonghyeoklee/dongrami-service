package com.dongrami.todo.dto;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TodoDto(
        @Schema(description = "PK")
        Long id,

        @Schema(description = "할 일 내용")
        String content,

        @Schema(description = "메모")
        String memo,

        @Schema(description = "할 일 상태")
        TodoStatus todoStatus,

        @Schema(description = "할 일 날짜")
        LocalDate todoDate,

        @Schema(description = "알림 시간")
        LocalDateTime notificationDateTime,

        @Schema(description = "핀 설정 여부")
        boolean isPinned,

        @Schema(description = "핀 설정 시간")
        LocalDateTime pinnedDateTime,

        @Schema(description = "생성 시간")
        LocalDateTime createdDateTime,

        @Schema(description = "수정 시간")
        LocalDateTime updatedDateTime
) {
    public static TodoDto of(TodoEntity todoEntity) {
        return new TodoDto(
                todoEntity.getId(),
                todoEntity.getContent(),
                todoEntity.getMemo(),
                todoEntity.getTodoStatus(),
                todoEntity.getTodoDate(),
                todoEntity.getNotificationDateTime(),
                todoEntity.isPinned(),
                todoEntity.getPinnedDateTime(),
                todoEntity.getCreatedDateTime(),
                todoEntity.getUpdatedDateTime()
        );
    }
}
