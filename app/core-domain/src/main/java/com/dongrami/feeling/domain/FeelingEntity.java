package com.dongrami.feeling.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "feeling")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FeelingEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("감정명")
    @Column(nullable = false)
    private String feelingName;

    @Comment("감정 이미지 URL")
    @Column(nullable = false)
    private String imageUrl;

    @Comment("삭제여부")
    @Column
    private boolean isDeleted;

    @Comment("감정 순서")
    @Column
    private int feelingOrder;

}
