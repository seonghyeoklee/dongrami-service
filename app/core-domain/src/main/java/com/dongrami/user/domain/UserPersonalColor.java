package com.dongrami.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class UserPersonalColor {

    @Comment("사용자 개인 컬러")
    @Column(length = 64, nullable = false)
    private String color;

}
