package com.dongrami.todo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseTodoAchievementRate(
        @Schema(description = "할일 달성률", example = "0.5")
        double achievementRate
) {
    public static ResponseTodoAchievementRate of(double achievementRate) {
        return new ResponseTodoAchievementRate(achievementRate);
    }
}
