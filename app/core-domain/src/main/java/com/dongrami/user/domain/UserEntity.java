package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.todo.domain.TodoEmojiEntity;
import com.dongrami.todo.domain.TodoEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
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
    private String emailVerifiedYn;

    @Comment("프로필 이미지 URL")
    @Setter
    @Column(length = 512)
    private String profileImageUrl;

    @Comment("제공자 타입")
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Comment("사용자 권한")
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Comment("사용자 차단 여부")
    @Column
    private boolean isBlocked;

    @Comment("사용자 차단 사유")
    @Column(length = 512)
    private String blockReason;

    @Embedded
    private PartnerCode partnerCode;

    @Embedded
    private UserPersonalColor userPersonalColor;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_group_id", foreignKey = @ForeignKey(name = "fk_user_user_group"))
    private UserGroupEntity userGroupEntity;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEntity> todoEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEmojiEntity> todoEmojiEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryEntity> diaryEntities = new ArrayList<>();

    public static UserEntity createUser(
            String userId,
            String username,
            String email,
            String emailVerifiedYn,
            String profileImageUrl,
            ProviderType providerType,
            RoleType roleType
    ) {
        return new UserEntity(
                userId,
                username,
                email,
                emailVerifiedYn,
                profileImageUrl,
                providerType,
                roleType
        );
    }

    private UserEntity(
            String userUniqueId,
            String username,
            String email,
            String emailVerifiedYn,
            String profileImageUrl,
            ProviderType providerType,
            RoleType roleType
    ) {
        this.userUniqueId = userUniqueId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.userPersonalColor = UserPersonalColor.builder().color("#f0f8ff").build();
    }

    public boolean hasUserGroup() {
        return userGroupEntity != null;
    }

}
