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
public class InviteCode {

    @Comment("짝꿍 초대 코드")
    @Column(length = 64, unique = true)
    private String inviteCode;

    @Builder
    public InviteCode(String inviteCode) {
        validateInviteCode(inviteCode);

        this.inviteCode = inviteCode;
    }

    private void validateInviteCode(String inviteCode) {
        if (inviteCode == null || inviteCode.isEmpty()) {
            throw new BaseException(ErrorCode.INVITE_CODE_EMPTY);
        }
    }

}
