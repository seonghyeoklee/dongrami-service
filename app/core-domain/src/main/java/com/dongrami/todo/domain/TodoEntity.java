package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
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

    @Comment("할일 날짜")
    @Column(nullable = false)
    private LocalDate todoDate;

    @Comment("할일 알림")
    @Column
    private LocalDateTime notificationDateTime;

    @Comment("할일 핀셋")
    @Column
    private boolean isPinned;

    @Comment("할일 핀셋 설정 시간")
    @Column
    private LocalDateTime pinnedDateTime;

    @Comment("할일 작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_todo_user"))
    private UserEntity userEntity;

    @Embedded
    private TodoEmojiEntities todoEmojiEntities;

    public static TodoEntity create(String content, String memo, LocalDateTime notificationDateTime, TodoStatus todoStatus, UserEntity userEntity) {
        return TodoEntity.builder()
                .content(content)
                .memo(memo)
                .todoStatus(todoStatus)
                .todoDate(LocalDate.now())
                .notificationDateTime(notificationDateTime)
                .isPinned(false)
                .pinnedDateTime(null)
                .userEntity(userEntity)
                .todoEmojiEntities(new TodoEmojiEntities())
                .build();
    }

    public void update(UserEntity userEntity, String content, TodoStatus todoStatus) {
        validateUser(userEntity);
        this.content = content;
        this.todoStatus = todoStatus;
    }

    public boolean isCompleted() {
        return this.todoStatus.isCompleted();
    }

    public boolean isOwner(UserEntity userEntity) {
        return this.userEntity.equals(userEntity);
    }

    public void delete(UserEntity userEntity) {
        validateUser(userEntity);

        if (this.todoStatus.isDeleted()) {
            throw new BaseException(ErrorCode.HANDLE_ALREADY_DELETED);
        }

        this.todoStatus = TodoStatus.DELETED;
    }

    private void validateUser(UserEntity userEntity) {
        if (!isOwner(userEntity)) {
            throw new BaseException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
    }

    public void changeTodoStatus(UserEntity userEntity) {
        validateUser(userEntity);

        if (this.todoStatus.isCompleted()) {
            this.todoStatus = TodoStatus.NOT_COMPLETED;
        } else {
            this.todoStatus = TodoStatus.COMPLETED;
        }
    }

    public void changeTodoPinned(UserEntity userEntity, boolean isPinned) {
        validateUser(userEntity);
        this.isPinned = isPinned;
        this.pinnedDateTime = isPinned ? LocalDateTime.now() : null;
    }

    public void addTodoEmoji(TodoEmojiEntity todoEmojiEntity) {
        todoEmojiEntities.add(todoEmojiEntity);
    }

}
