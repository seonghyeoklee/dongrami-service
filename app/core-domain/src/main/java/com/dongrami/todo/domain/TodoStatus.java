package com.dongrami.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TodoStatus {
    TODO("TODO", "할 일"),
    IN_PROGRESS("IN_PROGRESS", "진행 중"),
    DONE("DONE", "완료"),
    ;

    private final String code;
    private final String description;

    public static TodoStatus from(String code) {
        return Arrays.stream(TodoStatus.values())
                .filter(todoStatus -> todoStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isCompleted() {
        return this == DONE;
    }

}
