package com.dongrami.feeling.dto;

import com.dongrami.feeling.domain.FeelingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeelingDto {
    private Long id;
    private String feelingName;
    private String imageUrl;

    public static FeelingDto from(FeelingEntity entity) {
        return FeelingDto.builder()
                .id(entity.getId())
                .feelingName(entity.getFeelingName())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
