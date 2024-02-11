package com.dongrami.user.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String groupName;

    @Column(length = 512)
    private String groupDescription;

    @OneToMany(mappedBy = "userGroupEntity")
    private List<UserEntity> userEntities = new ArrayList<>();

}
