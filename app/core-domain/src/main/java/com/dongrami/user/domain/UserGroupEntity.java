package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_group")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserGroupEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("그룹 이름")
    @Column(length = 128, nullable = false)
    private String groupName;

    @Comment("그룹에 대한 설명")
    @Column(length = 512)
    private String groupDescription;

    @Comment("그룹 코드")
    @Column(length = 128, nullable = false)
    private String groupCode;

    @OneToMany(mappedBy = "userGroupEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> userEntities = new ArrayList<>();

}
