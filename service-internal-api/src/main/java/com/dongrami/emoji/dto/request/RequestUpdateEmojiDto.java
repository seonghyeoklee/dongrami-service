package com.dongrami.emoji.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestUpdateEmojiDto {

    @Schema(description = "이모지 이름", example = "행복")
    @NotBlank(message = "이모지 이름을 입력해주세요.")
    private String name;

    @Schema(description = "이모지 아이콘", example = "😀")
    @NotBlank(message = "아이콘을 입력해주세요.")
    private String icon;

    @Schema(description = "삭제 여부", example = "false")
    @NotNull(message = "삭제 여부를 입력해주세요.")
    private Boolean isDeleted;
}
