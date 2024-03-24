package com.dongrami.diary.domain;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryDeleteInfo {

    @Comment("삭제 시간")
    @Column
    private LocalDateTime deletedDateTime;

    @Comment("삭제 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_user_id", foreignKey = @ForeignKey(name = "fk_diary_deleted_user"))
    private UserEntity deletedUserEntity;

    public DiaryDeleteInfo(UserEntity deletedUserEntity) {
        validateDeletedUserEntity(deletedUserEntity);
        this.deletedDateTime = LocalDateTime.now();
        this.deletedUserEntity = deletedUserEntity;
    }

    private void validateDeletedUserEntity(UserEntity deletedUserEntity) {
        if (deletedUserEntity == null) {
            throw new BaseException(ErrorCode.DELETE_USER_NOT_NULL);
        }
    }

}
