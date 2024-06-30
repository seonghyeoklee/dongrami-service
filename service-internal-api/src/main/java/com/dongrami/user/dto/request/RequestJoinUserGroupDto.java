package com.dongrami.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestJoinUserGroupDto {

    @Schema(description = "그룹 코드", example = "1234")
    @NotBlank(message = "그룹 코드를 입력해주세요.")
    private String groupCode;

}
