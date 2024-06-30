package com.dongrami.code.dto.request;

import lombok.Data;

@Data
public class RequestUpdateCodeGroupDto {
    private String codeGroup;
    private String description;
    private boolean isDeleted;
}
