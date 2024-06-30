package com.dongrami.diary.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RequestCreateDiaryDto {

    @Schema(description = "일기 제목", example = "제목")
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Schema(description = "일기 내용", example = "오늘 하루는 어땠나요?")
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Schema(description = "일기 날씨", example = "맑음")
    private String weather;

    @Schema(description = "일기 기분 ID", example = "1")
    private Long feelingId;

    @Schema(description = "일기 사진", example = "사진")
    private String picture;

    @Schema(description = "일기 공개여부", example = "true")
    private Boolean isPublic;

    @Schema(description = "태그 ID", example = "[1, 2, 3]")
    private List<Long> tags;

}
