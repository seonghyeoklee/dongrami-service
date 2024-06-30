package com.dongrami.code.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "code")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CodeEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("코드 그룹")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_group_id", foreignKey = @ForeignKey(name = "fk_code_code_group"))
    private CodeGroupEntity codeGroupEntity;

    @Comment("코드")
    @Column(length = 64, nullable = false)
    private String code;

    @Comment("코드 명")
    @Column(length = 64, nullable = false)
    private String codeName;

    @Comment("코드 설명")
    @Column(length = 256, nullable = false)
    private String description;

    @Comment("삭제 여부")
    @Column
    private boolean isDeleted;

    @Comment("순서")
    @Column
    private int orders;

}
