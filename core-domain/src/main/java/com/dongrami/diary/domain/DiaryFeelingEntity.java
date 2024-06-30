package com.dongrami.diary.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.feeling.domain.FeelingEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "diary_feeling")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryFeelingEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("일기")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", foreignKey = @ForeignKey(name = "fk_diary_feeling_diary"))
    private DiaryEntity diaryEntity;

    @Comment("감정")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feeling_id", foreignKey = @ForeignKey(name = "fk_diary_feeling_feeling"))
    private FeelingEntity feelingEntity;

    @Comment("사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_diary_feeling_user"))
    private UserEntity userEntity;

    @Comment("감정 강도")
    @Column
    private int feelingIntensity;

}
