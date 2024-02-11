package com.dongrami.diary.domain;

import com.dongrami.common.BaseTimeEntity;
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

    @OneToMany(mappedBy = "diaryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryFileEntity> diaryFileEntities = new ArrayList<>();

}
