package com.dongrami.diary.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.file.domain.UploadFileEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "diary_file")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryFileEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("일기")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", foreignKey = @ForeignKey(name = "fk_diary_file_diary"))
    private DiaryEntity diaryEntity;

    @Comment("업로드 파일")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_file_id", foreignKey = @ForeignKey(name = "fk_diary_file_upload_file"))
    private UploadFileEntity uploadFileEntity;

}
