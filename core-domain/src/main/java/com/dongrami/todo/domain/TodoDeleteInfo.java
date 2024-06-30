package com.dongrami.todo.domain;

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
public class TodoDeleteInfo {

    @Comment("삭제 시간")
    @Column
    private LocalDateTime deletedDateTime;

    @Comment("할일 삭제 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_user_id", foreignKey = @ForeignKey(name = "fk_todo_deleted_user"))
    private UserEntity deletedUserEntity;

    public TodoDeleteInfo(UserEntity deletedUserEntity) {
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
