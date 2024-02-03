package com.dongrami.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderType {
    GOOGLE("구글"),
    NAVER("네이버"),
    KAKAO("카카오"),
    LOCAL("기본"),
    ;

    private final String description;
}
