package com.dongrami.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TodoStatus {
    NOT_COMPLETED("NOT_COMPLETED", "미완료"),
    COMPLETED("COMPLETED", "완료"),
    DELETED("DELETED", "삭제"),
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
        return this == COMPLETED;
    }

}
