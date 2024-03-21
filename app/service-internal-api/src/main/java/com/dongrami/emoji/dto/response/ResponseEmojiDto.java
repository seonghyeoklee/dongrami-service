package com.dongrami.emoji.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmojiDto {

    @Schema(description = "이모지 ID", example = "1")
    private Long id;

    @Schema(description = "이모지 이름", example = "행복")
    private String name;

    @Schema(description = "이모지 아이콘", example = "😀")
    private String icon;

    @Schema(description = "삭제 여부", example = "false")
    @JsonProperty("isDeleted")
    private boolean isDeleted;

}
