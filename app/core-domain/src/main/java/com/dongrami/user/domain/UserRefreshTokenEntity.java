package com.dongrami.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_token")
public class UserRefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, unique = true)
    private String userId;

    @Column(length = 256)
    private String refreshToken;

    public UserRefreshTokenEntity(String userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
