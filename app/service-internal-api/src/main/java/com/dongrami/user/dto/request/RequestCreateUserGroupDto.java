package com.dongrami.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestCreateUserGroupDto {

    @Schema(description = "그룹 이름", example = "우리집")
    @NotBlank(message = "그룹 이름을 입력해주세요.")
    private String groupName;

    @Schema(description = "그룹에 대한 설명", example = "우리집에 있는 사람들")
    private String groupDescription;

}
