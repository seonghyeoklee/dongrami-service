package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_token")
public class UserRefreshTokenEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자 아이디")
    @Column(length = 64, unique = true, nullable = false)
    private String userId;

    @Comment("리프레시 토큰")
    @Column(length = 256, nullable = false)
    private String refreshToken;

    public UserRefreshTokenEntity(String userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

}
