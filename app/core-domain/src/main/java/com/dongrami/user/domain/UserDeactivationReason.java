package com.dongrami.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserDeactivationReason {
    INFREQUENT_USE("INFREQUENT_USE", "사용 빈도가 낮아요"),
    NO_DESIRED_FEATURE("NO_DESIRED_FEATURE", "원하는 기능이 없어요"),
    INCONVENIENT("INCONVENIENT", "사용하기 불편해요"),
    CONCERNED_ABOUT_PRIVACY_AND_SECURITY("CONCERNED_ABOUT_PRIVACY_AND_SECURITY", "개인정보 및 보안이 걱정되요"),
    OTHER("OTHER", "기타");

    private final String code;
    private final String description;
}
