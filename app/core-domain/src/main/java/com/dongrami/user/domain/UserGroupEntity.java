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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserGroupEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("그룹 이름")
    @Column(length = 128)
    private String groupName;

    @Comment("그룹에 대한 설명")
    @Column(length = 512)
    private String groupDescription;

    @Comment("그룹 코드")
    @Column(length = 128, nullable = false, unique = true)
    private String groupCode;

    @Comment("그룹 생성자")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private UserEntity ownerUserEntity;

    @Comment("그룹 참여자")
    @OneToMany(mappedBy = "userGroupEntity", cascade = CascadeType.PERSIST)
    private List<UserEntity> userEntities = new ArrayList<>();

    @Builder
    public UserGroupEntity(Long id, String groupName, String groupDescription, String groupCode, UserEntity userEntity) {
        this.id = id;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.groupCode = groupCode;
        this.ownerUserEntity = userEntity;
        this.addUserEntity(userEntity);
    }

    public void addUserEntity(UserEntity userEntity) {
        userEntities.add(userEntity);
        userEntity.setUserGroupEntity(this);
    }

    public void removeAllUser() {
        userEntities.forEach(userEntity -> userEntity.setUserGroupEntity(null));
    }

    public boolean isOwner(UserEntity userEntity) {
        return ownerUserEntity.equals(userEntity);
    }

    public void removeUserEntity(UserEntity userEntity) {
        userEntities.remove(userEntity);
        userEntity.setUserGroupEntity(null);
    }

    public void updateGroupInfo(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }
}
