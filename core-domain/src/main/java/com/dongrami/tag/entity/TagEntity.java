package com.dongrami.tag.entity;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.diary.domain.DiaryTagEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = {"tagName", "userEntity"}, callSuper = false)
public class TagEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("태그명")
    @Column(length = 256, nullable = false)
    private String tagName;

    @Comment("태그 생성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_tag_user"))
    private UserEntity userEntity;

    @OneToMany(mappedBy = "tagEntity")
    private List<DiaryTagEntity> diaryTags = new ArrayList<>();

}
