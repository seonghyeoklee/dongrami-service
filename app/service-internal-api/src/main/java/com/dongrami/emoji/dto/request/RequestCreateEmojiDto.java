package com.dongrami.emoji.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestCreateEmojiDto {

    @Schema(description = "이모지 이름", example = "행복")
    @NotBlank(message = "이모지 이름을 입력해주세요.")
    private String name;

    @Schema(description = "이모지 아이콘", example = "😀")
    @NotBlank(message = "아이콘을 입력해주세요.")
    private String icon;

}
