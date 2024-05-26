package com.dongrami.user.dto;

import com.dongrami.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserDto {
    private Long id;
    private String username;
    private String email;
    private String profileImageUrl;
    private String color;

    public static SimpleUserDto from(UserEntity userEntity) {
        return SimpleUserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .profileImageUrl(userEntity.getProfileInfo().getProfileImageUrl())
                .color(userEntity.getProfileInfo().getUserPersonalColor().getColor())
                .build();
    }

}
