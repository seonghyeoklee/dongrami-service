package com.dongrami.diary.dto;

import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {
    private Long id;
    private String title;
    private String content;
    private UserDto user;
    private boolean isPublic;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static DiaryDto from(DiaryEntity diaryEntity) {
        return DiaryDto.builder()
                .id(diaryEntity.getId())
                .title(diaryEntity.getTitle())
                .content(diaryEntity.getContent())
                .user(UserDto.from(diaryEntity.getUserEntity()))
                .isPublic(diaryEntity.isPublic())
                .createdDateTime(diaryEntity.getCreatedDateTime())
                .updatedDateTime(diaryEntity.getUpdatedDateTime())
                .build();
    }
}
