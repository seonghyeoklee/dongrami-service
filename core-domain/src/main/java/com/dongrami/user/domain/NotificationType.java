package com.dongrami.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    INFORMATION("정보성 알림"),
    TODO_REMINDER("할 일 리마인드 알림"),
    TODO("할 일 알림 설정"),
    PAIR_NEW_TODO("짝꿍의 할 일 새로 등록 알림"),
    PAIR_COMPLETE_TODO("짝꿍의 할 일 완료 알림"),
    PAIR_EMOJI("짝꿍의 이모지 알림"),
    PAIR_DIARY("짝꿍의 일기 작성 알림"),
    MENSTRUAL("월경 알림");

    private final String description;
}
