package com.dongrami.tag.dto.response;

import com.dongrami.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTagDto {
    private Long id;
    private String tagName;
    private UserDto user;
}
