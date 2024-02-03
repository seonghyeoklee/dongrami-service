package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true)
    private String userId;

    @Setter
    @Column(length = 100)
    private String username;

    @Column(length = 128)
    private String password;

    @Column(length = 512, unique = true)
    private String email;

    @Column(length = 1)
    private String emailVerifiedYn;

    @Setter
    @Column(length = 512)
    private String profileImageUrl;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public UserEntity(
            String userId,
            String username,
            String email,
            String emailVerifiedYn,
            String profileImageUrl,
            ProviderType providerType,
            RoleType roleType
    ) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
    }

}
