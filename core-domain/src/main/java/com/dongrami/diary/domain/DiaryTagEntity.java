package com.dongrami.diary.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.tag.entity.TagEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "diary_tag")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = {"diaryEntity", "tagEntity", "userEntity"}, callSuper = false)
public class DiaryTagEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Comment("일기")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", foreignKey = @ForeignKey(name = "fk_diary_tag_diary"))
    private DiaryEntity diaryEntity;

    @Comment("태그")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "fk_diary_tag_tag"))
    private TagEntity tagEntity;

    @Comment("태그 생성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_diary_tag_user"))
    private UserEntity userEntity;

}
