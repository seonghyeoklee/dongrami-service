package com.dongrami.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestUpdateUserGroupDto {

    @Schema(description = "그룹 이름", example = "우리 가족")
    @NotBlank(message = "그룹 이름을 입력해주세요.")
    private String groupName;

    @Schema(description = "그룹 설명", example = "우리집에 있는 사람들")
    @NotBlank(message = "그룹 설명을 입력해주세요.")
    private String groupDescription;

}
