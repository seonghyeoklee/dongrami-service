package com.dongrami.file.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "upload_file")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UploadFileEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Comment("원본 파일명")
    @Column(nullable = false, length = 100)
    private String originFileName;

    @Comment("파일 key")
    @Column(nullable = false, length = 128)
    private String fileKey;

    @Comment("파일 크기")
    @Column(nullable = false)
    private int fileSize;

    @Comment("파일 URL")
    @Column(nullable = false, length = 256)
    private String url;

    @Comment("파일 타입")
    @Column(nullable = false, length = 20)
    private String fileType;

}
