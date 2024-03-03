package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "todo")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("할일 내용")
    @Column(length = 512, nullable = false)
    private String content;

    @Comment("할일 메모")
    @Column(length = 512)
    private String memo;

    @Comment("할일 상태")
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @Comment("할일 작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_todo_user"))
    private UserEntity userEntity;

    @Comment("할일 삭제여부")
    @Column
    private boolean isDeleted;

    @Embedded
    private TodoEmojiEntities todoEmojiEntities;

    @Embedded
    private TodoNotificationEntities todoNotificationEntities;

    public static TodoEntity create(String content, String memo, TodoStatus todoStatus, UserEntity userEntity) {
        return TodoEntity.builder()
                .content(content)
                .memo(memo)
                .todoStatus(todoStatus)
                .userEntity(userEntity)
                .isDeleted(false)
                .todoEmojiEntities(new TodoEmojiEntities())
                .todoNotificationEntities(new TodoNotificationEntities())
                .build();
    }

    public void update(UserEntity userEntity, String content, TodoStatus todoStatus) {
        validateUser(userEntity);
        this.content = content;
        this.todoStatus = todoStatus;
    }

    public void addTodoNotification(TodoNotificationEntity todoNotificationEntity) {
        this.todoNotificationEntities.add(todoNotificationEntity);
        todoNotificationEntity.setTodoEntity(this);
    }

    public boolean isCompleted() {
        return this.todoStatus.isCompleted();
    }

    public boolean isOwner(UserEntity userEntity) {
        return this.userEntity.equals(userEntity);
    }

    public void delete(UserEntity userEntity) {
        validateUser(userEntity);
        this.isDeleted = true;
    }

    private void validateUser(UserEntity userEntity) {
        if (!isOwner(userEntity)) {
            throw new BaseException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
    }
}
