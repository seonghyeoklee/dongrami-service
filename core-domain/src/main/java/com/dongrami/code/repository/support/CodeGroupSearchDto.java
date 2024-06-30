package com.dongrami.code.repository.support;

import lombok.Data;

@Data
public class CodeGroupSearchDto {
    private String codeGroup;
    private String description;
    private boolean isDeleted;
}
