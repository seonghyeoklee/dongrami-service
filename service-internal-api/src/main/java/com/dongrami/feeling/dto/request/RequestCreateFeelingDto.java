package com.dongrami.feeling.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestCreateFeelingDto {

    @Schema(description = "감정명", example = "행복함")
    @NotBlank(message = "감정명을 입력해주세요.")
    private String feelingName;

    @Schema(description = "감정 이미지 URL", example = "https://image.com")
    @NotBlank(message = "감정 이미지 URL을 입력해주세요.")
    private String imageUrl;

    @Schema(description = "정렬 순서", example = "1")
    private int feelingOrder;

}
