package com.dongrami.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Embeddable
@NoArgsConstructor
public class ProfileInfo {

    @Comment("프로필 이미지 URL")
    @Setter
    @Column(length = 512)
    private String profileImageUrl;

    @Comment("사용자 닉네임")
    @Column(length = 100)
    private String nickname;

    @Comment("사용자 지역 정보")
    @Enumerated(EnumType.STRING)
    private Location location;

    @Comment("사용자 개인 컬러 정보")
    @Embedded
    private UserPersonalColor userPersonalColor;

    @Comment("초대 코드")
    @Embedded
    private InviteCode inviteCode;

    @Comment("월경 기능 사용여부")
    @Column
    private boolean isMenstrualCycle;

    @Builder
    public ProfileInfo(String profileImageUrl, String nickname, Location location, UserPersonalColor userPersonalColor, InviteCode inviteCode, boolean isMenstrualCycle) {
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.location = location;
        this.userPersonalColor = userPersonalColor;
        this.inviteCode = inviteCode;
        this.isMenstrualCycle = isMenstrualCycle;
    }

    public void updateProfileInfo(String nickname, Location location) {
        this.nickname = nickname;
        this.location = location;
    }

    public void updateMenstrual() {
        this.isMenstrualCycle = !this.isMenstrualCycle;
    }
}
