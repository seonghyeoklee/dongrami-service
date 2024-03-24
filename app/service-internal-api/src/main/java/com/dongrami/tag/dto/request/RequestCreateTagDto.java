package com.dongrami.tag.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestCreateTagDto {

    @Schema(description = "태그명", example = "#육아")
    @NotBlank(message = "태그명을 입력해주세요.")
    private String name;

    public String getName() {
        return name.trim();
    }

}
