package com.dongrami.code.dto.response;

import lombok.Data;

@Data
public class ResponseCodeGroupDto {
    private Long id;
    private String codeGroup;
    private String description;
    private boolean isDeleted;
}
