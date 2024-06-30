package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_deactivation")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDeactivationEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Comment("탈퇴일")
    @Column(nullable = false)
    private LocalDateTime deactivationDate;

    @Comment("탈퇴 사유")
    @Enumerated(EnumType.STRING)
    private UserDeactivationReason userDeactivationReason;

    @Comment("탈퇴 상세 의견")
    @Column(nullable = false)
    private String deactivationDetailOpinion;

}
