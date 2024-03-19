package com.dongrami.user.dto.response;

import com.dongrami.user.domain.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class ResponseTodoUserInfoDto {
    private Long id;
    private String email;
    private String username;
    private String userPersonalColor;

    @Builder
    public ResponseTodoUserInfoDto(Long id, String email, String username, String userPersonalColor) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.userPersonalColor = userPersonalColor;
    }

    public static ResponseTodoUserInfoDto from(UserEntity entity) {
        return ResponseTodoUserInfoDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .userPersonalColor(entity.getUserPersonalColor().getColor())
                .build();
    }
}
