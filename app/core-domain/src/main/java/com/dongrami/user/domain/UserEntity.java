package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.todo.domain.TodoEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_group_id")
    private UserGroupEntity userGroupEntity;

    @OneToMany(mappedBy = "userEntity")
    private List<TodoEntity> todoEntities = new ArrayList<>();

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
