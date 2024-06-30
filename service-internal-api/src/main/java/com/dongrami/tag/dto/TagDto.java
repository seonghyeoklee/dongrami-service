package com.dongrami.tag.dto;

import com.dongrami.tag.entity.TagEntity;
import com.dongrami.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private Long id;
    private String tagName;
    private UserDto user;

    public static TagDto from(TagEntity tagEntity) {
        return TagDto.builder()
                .id(tagEntity.getId())
                .tagName(tagEntity.getTagName())
                .user(UserDto.of(tagEntity.getUserEntity()))
                .build();
    }
}
