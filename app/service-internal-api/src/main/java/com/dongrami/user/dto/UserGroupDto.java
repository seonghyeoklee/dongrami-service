package com.dongrami.user.dto;

import com.dongrami.user.domain.UserGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupDto {
    private Long id;
    private String groupName;
    private String groupDescription;
    private String groupCode;
    private List<SimpleUserDto> users = new ArrayList<>();

    public static UserGroupDto from(UserGroupEntity userGroupEntity) {
        return UserGroupDto.builder()
                .id(userGroupEntity.getId())
                .groupName(userGroupEntity.getGroupName())
                .groupDescription(userGroupEntity.getGroupDescription())
                .groupCode(userGroupEntity.getGroupCode())
                .users(
                        userGroupEntity.getUserEntities().stream()
                                .map(SimpleUserDto::from)
                                .toList()
                )
                .build();
    }

}
