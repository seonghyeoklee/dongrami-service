package com.dongrami.user.domain;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import lombok.Builder;
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
    @Column(length = 64)
    private String color = "#000000";

    @Builder
    public UserPersonalColor(String color) {
        validateColor(color);
        this.color = color;
    }

    private void validateColor(String color) {
        if (color == null || color.isEmpty()) {
            throw new BaseException(ErrorCode.NO_CONTENT);
        }
    }

}
