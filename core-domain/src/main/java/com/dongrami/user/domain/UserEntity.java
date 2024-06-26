package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.todo.domain.TodoEmojiEntity;
import com.dongrami.todo.domain.TodoEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class UserEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자 고유 아이디")
    @Column(length = 64, unique = true)
    private String userUniqueId;

    @Comment("사용자 이름")
    @Setter
    @Column(length = 100)
    private String username;

    @Comment("비밀번호")
    @Column(length = 128)
    private String password;

    @Comment("이메일")
    @Column(length = 512, unique = true)
    private String email;

    @Comment("이메일 인증 여부")
    @Column(length = 1)
    private boolean isEmailVerified;

    @Comment("제공자 타입")
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Comment("사용자 권한")
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Comment("전화번호")
    @Column(length = 20)
    private String phoneNumber;

    @Comment("프로필 정보")
    @Embedded
    private ProfileInfo profileInfo;

    @Comment("짝꿍")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pair_user_id")
    private UserEntity pairUserEntity;

    @Comment("짝꿍 설정 시간")
    @Column
    private LocalDateTime pairUserSettingTime;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEntity> todoEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEmojiEntity> todoEmojiEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryEntity> diaryEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserNotificationSettingEntity> userNotificationSettingEntities = new ArrayList<>();

    public static UserEntity createUser(
            String userId,
            String username,
            String email,
            boolean emailVerifiedYn,
            String profileImageUrl,
            ProviderType providerType,
            RoleType roleType,
            String inviteCode
    ) {
        return new UserEntity(
                userId,
                username,
                email,
                emailVerifiedYn,
                profileImageUrl,
                providerType,
                roleType,
                inviteCode
        );
    }

    private UserEntity(
            String userUniqueId,
            String username,
            String email,
            boolean isEmailVerified,
            String profileImageUrl,
            ProviderType providerType,
            RoleType roleType,
            String inviteCode
    ) {
        this.userUniqueId = userUniqueId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.isEmailVerified = isEmailVerified;
        this.providerType = providerType;
        this.roleType = roleType;
        this.profileInfo = ProfileInfo.builder()
                .inviteCode(InviteCode.of(inviteCode))
                .userPersonalColor(UserPersonalColor.of("#f0f8ff"))
                .profileImageUrl(profileImageUrl != null ? profileImageUrl : "")
                .build();
    }

    public void updateProfileInfo(String nickname, Location location) {
        this.profileInfo.updateProfileInfo(nickname, location);
    }

    public void updateMenstrual() {
        this.profileInfo.updateMenstrual();
    }

    public String getInviteCode() {
        return this.profileInfo.getInviteCode().getInviteCode();
    }

    public void updatePairUserEntity(UserEntity userEntity) {
        this.pairUserEntity = userEntity;
        this.pairUserSettingTime = LocalDateTime.now();
    }

    public void addUserNotificationSettingEntities(UserNotificationSettingEntity userNotificationSettingEntity) {
        if (userNotificationSettingEntities != null) {
            this.userNotificationSettingEntities.add(userNotificationSettingEntity);
        }
    }

    public void deletePairUserEntity() {
        this.pairUserEntity = null;
        this.pairUserSettingTime = null;
    }
}
