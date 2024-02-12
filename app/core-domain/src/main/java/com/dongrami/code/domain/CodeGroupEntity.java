package com.dongrami.code.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "code_group")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CodeGroupEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("코드 그룹")
    @Column(length = 64, nullable = false)
    private String codeGroup;

    @Comment("코드 그룹 설명")
    @Column(length = 256, nullable = false)
    private String description;

    @Comment("삭제 여부")
    @Column
    private boolean isDeleted;

    @OneToMany(mappedBy = "codeGroupEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CodeEntity> codeEntities = new ArrayList<>();

}
