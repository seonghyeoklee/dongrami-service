package com.dongrami.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TodoStatus {
    TODO("TODO", "할 일"),
    IN_PROGRESS("IN_PROGRESS", "진행 중"),
    DONE("DONE", "완료"),
    ;

    private final String code;
    private final String description;
}
