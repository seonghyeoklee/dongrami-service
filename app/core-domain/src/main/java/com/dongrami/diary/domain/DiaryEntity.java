package com.dongrami.diary.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.tag.domain.TagEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diary")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("일기 제목")
    @Column(length = 256, nullable = false)
    private String title;

    @Comment("일기 내용")
    @Column(length = 1000, nullable = false)
    private String content;

    @Comment("일기 작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_diary_user"))
    private UserEntity userEntity;

    @Comment("일기 공개 여부")
    @Column
    private boolean isPublic;

    @OneToMany(mappedBy = "diaryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryFileEntity> diaryFileEntities = new ArrayList<>();

    @OneToMany(mappedBy = "diaryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagEntity> tagEntities = new ArrayList<>();

    public static DiaryEntity create(UserEntity userEntity, String title, String content, boolean isPublic) {
        return DiaryEntity.builder()
                .userEntity(userEntity)
                .title(title)
                .content(content)
                .isPublic(isPublic)
                .build();
    }

}
