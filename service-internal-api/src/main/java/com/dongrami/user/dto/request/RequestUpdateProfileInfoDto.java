package com.dongrami.user.dto.request;

import com.dongrami.user.domain.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestUpdateProfileInfoDto {

    @Schema(description = "닉네임", example = "동글한라미")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @Schema(description = "지역", example = "SEOUL")
    private Location location;

}
