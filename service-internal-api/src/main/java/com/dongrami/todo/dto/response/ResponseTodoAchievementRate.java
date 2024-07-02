package com.dongrami.todo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseTodoAchievementRate(
        @Schema(description = "할일 달성률", example = "30")
        int achievementRate
) {
    public static ResponseTodoAchievementRate of(int achievementRate) {
        return new ResponseTodoAchievementRate(achievementRate);
    }
}
