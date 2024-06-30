package com.dongrami.user.dto.response;

import com.dongrami.user.domain.Location;
import com.dongrami.user.domain.ProviderType;
import com.dongrami.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProfileInfoDto {
    private Long id;
    private String nickname;
    private String email;
    private ProviderType providerType;
    private String phoneNumber;
    private Location location;
    private boolean isMenstrualCycle;

    public static ResponseProfileInfoDto from(UserEntity entity) {
        return ResponseProfileInfoDto.builder()
                .id(entity.getId())
                .nickname(entity.getProfileInfo().getNickname())
                .email(entity.getEmail())
                .providerType(entity.getProviderType())
                .phoneNumber(entity.getPhoneNumber())
                .location(entity.getProfileInfo().getLocation())
                .isMenstrualCycle(entity.getProfileInfo().isMenstrualCycle())
                .build();
    }
}
