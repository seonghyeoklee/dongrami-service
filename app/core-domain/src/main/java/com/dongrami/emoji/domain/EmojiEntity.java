package com.dongrami.emoji.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "emoji")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EmojiEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이모지 이름")
    @Column(length = 64, nullable = false, unique = true)
    private String name;

    @Comment("이모지 아이콘")
    @Column(length = 64, nullable = false, unique = true)
    private String icon;

    @Comment("삭제 여부")
    @Column
    private boolean isDeleted;

}
