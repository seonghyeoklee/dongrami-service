package com.dongrami.terms.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "user_terms")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserTermsEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_terms_user"))
    private UserEntity userEntity;

    @Comment("이용약관")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id", foreignKey = @ForeignKey(name = "fk_user_terms_terms"))
    private TermsEntity termsEntity;

    @Comment("동의 여부")
    @Column
    private boolean isAgreed;

    @Comment("동의 만료 기한")
    @Column
    private boolean isExpired;

}