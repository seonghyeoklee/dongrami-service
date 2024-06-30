package com.dongrami.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseTodoAchievementRateDto {
    private String achievementRate;

    public static ResponseTodoAchievementRateDto from(int achievementRate) {
        return ResponseTodoAchievementRateDto.builder()
                .achievementRate(String.valueOf(achievementRate))
                .build();
    }
}
