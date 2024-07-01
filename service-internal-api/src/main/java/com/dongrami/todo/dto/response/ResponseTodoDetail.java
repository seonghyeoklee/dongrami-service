package com.dongrami.todo.dto.response;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import com.dongrami.user.dto.response.ResponseUserInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ResponseTodoDetail(
        @Schema(description = "PK")
        Long id,

        @Schema(description = "할 일 내용")
        String content,

        @Schema(description = "메모")
        String memo,

        @Schema(description = "할 일 상태")
        TodoStatus todoStatus,

        @Schema(description = "알림 시간")
        LocalDateTime notificationDateTime,

        @Schema(description = "핀 설정 여부")
        Boolean isPinned,

        @Schema(description = "핀 설정 시간")
        LocalDateTime pinnedDateTime,

        @Schema(description = "작성자 정보")
        ResponseUserInfo writerInfo,

        @Schema(description = "생성 시간")
        LocalDateTime createdDateTime,

        @Schema(description = "수정 시간")
        LocalDateTime updatedDateTime
) {
    public static ResponseTodoDetail of(TodoEntity todoEntity) {
        return new ResponseTodoDetail(
                todoEntity.getId(),
                todoEntity.getContent(),
                todoEntity.getMemo(),
                todoEntity.getTodoStatus(),
                todoEntity.getNotificationDateTime(),
                todoEntity.isPinned(),
                todoEntity.getPinnedDateTime(),
                ResponseUserInfo.of(todoEntity.getAuthorUserEntity()),
                todoEntity.getCreatedDateTime(),
                todoEntity.getUpdatedDateTime()
        );
    }
}
