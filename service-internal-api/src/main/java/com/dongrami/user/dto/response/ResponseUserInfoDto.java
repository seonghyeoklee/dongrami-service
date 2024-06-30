package com.dongrami.user.dto.response;

import com.dongrami.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserInfoDto {
    private Long id;
    private String email;
    private String username;
    private String userPersonalColor;

    public static ResponseUserInfoDto from(UserEntity entity) {
        return ResponseUserInfoDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .userPersonalColor(entity.getProfileInfo().getUserPersonalColor().getColor())
                .build();
    }
}
