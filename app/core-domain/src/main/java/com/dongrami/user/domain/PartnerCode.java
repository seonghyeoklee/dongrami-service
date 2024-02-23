package com.dongrami.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class PartnerCode {

    @Comment("파트너 초대 코드")
    @Column(length = 64, unique = true)
    private String inviteCode;

    @Builder
    public PartnerCode(String inviteCode) {
        validateInviteCode(inviteCode);

        this.inviteCode = inviteCode;
    }

    private void validateInviteCode(String inviteCode) {
        if (inviteCode == null || inviteCode.isEmpty()) {
            throw new IllegalArgumentException("파트너 초대 코드는 필수입니다.");
        }
    }

}
