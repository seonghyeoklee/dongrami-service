package com.dongrami.diary.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.tag.entity.TagEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Comment("일기 작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_diary_user"))
    private UserEntity userEntity;

    @Comment("일기 작성일")
    @Column(nullable = false)
    private LocalDate writtenDate;

    @Comment("일기 공개 여부")
    @Column
    private boolean isPublic;

    @Comment("일기 삭제 여부")
    @Column
    private boolean isDeleted;

    @OneToMany(mappedBy = "diaryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryFileEntity> diaryFileEntities = new ArrayList<>();

    @OneToMany(mappedBy = "diaryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryFeelingEntity> diaryFeelingEntities = new ArrayList<>();

    @Embedded
    private DiaryTagEntities diaryTagEntities;

    @Embedded
    private DiaryDeleteInfo diaryDeleteInfo;

    public static DiaryEntity create(UserEntity userEntity, String title, String content, boolean isPublic) {
        return DiaryEntity.builder()
                .userEntity(userEntity)
                .title(title)
                .writtenDate(LocalDate.now())
                .content(content)
                .isPublic(isPublic)
                .diaryTagEntities(new DiaryTagEntities())
                .build();
    }

    public void update(String title, String content, boolean isPublic) {
        if (this.isDeleted) {
            throw new BaseException(ErrorCode.DIARY_ALREADY_DELETED_CANNOT_UPDATE);
        }
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
    }

    public void delete() {
        if (this.isDeleted) {
            throw new BaseException(ErrorCode.DIARY_ALREADY_DELETED_CANNOT_DELETE);
        }
        this.isDeleted = true;
        this.diaryDeleteInfo = new DiaryDeleteInfo(this.userEntity);
    }

    public boolean isOwner(UserEntity userEntity) {
        return this.userEntity.equals(userEntity);
    }

    public void addDiaryTags(List<TagEntity> tagEntities) {
        tagEntities.forEach(tagEntity -> {
            DiaryTagEntity diaryTagEntity = DiaryTagEntity.builder()
                    .tagEntity(tagEntity)
                    .diaryEntity(this)
                    .userEntity(this.userEntity)
                    .build();

            this.diaryTagEntities.add(diaryTagEntity);
        });
    }

    public void modifyDiaryTags(List<TagEntity> tagEntities) {
        this.diaryTagEntities.clear();
        addDiaryTags(tagEntities);
    }

}
