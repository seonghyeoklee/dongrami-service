package com.dongrami.user.dto.response;

public record ResponseCountTodoAndDiary(
        long todoCount,
        long diaryCount
) {
}
