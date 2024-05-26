package com.dongrami.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Location {
    SEOUL("SEOUL", "서울"),
    BUSAN("BUSAN", "부산"),
    DAEGU("DAEGU", "대구"),
    INCHEON("INCHEON", "인천"),
    GWANGJU("GWANGJU", "광주"),
    DAEJEON("DAEJEON", "대전"),
    ULSAN("ULSAN", "울산"),
    SEJONG("SEJONG", "세종"),
    GYEONGGI("GYEONGGI", "경기"),
    GANGWON("GANGWON", "강원"),
    CHUNGBUK("CHUNGBUK", "충북"),
    CHUNGNAM("CHUNGNAM", "충남"),
    JEONBUK("JEONBUK", "전북"),
    JEONNAM("JEONNAM", "전남"),
    GYEONGBUK("GYEONGBUK", "경북"),
    GYEONGNAM("GYEONGNAM", "경남"),
    JEJU("JEJU", "제주");

    private final String code;
    private final String description;
}
