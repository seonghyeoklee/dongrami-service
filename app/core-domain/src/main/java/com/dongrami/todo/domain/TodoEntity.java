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
import java.util.List;

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

    public void update(String content, TodoStatus todoStatus) {
        this.content = content;
        this.todoStatus = todoStatus;
    }

    public boolean isCompleted() {
        return this.todoStatus.isCompleted();
    }

    public void delete() {
        if (this.todoStatus.isDeleted()) {
            throw new BaseException(ErrorCode.TODO_ALREADY_DELETED);
        }
        this.todoStatus = TodoStatus.DELETED;
    }

    public void changeTodoStatus() {
        if (this.todoStatus.isCompleted()) {
            this.todoStatus = TodoStatus.NOT_COMPLETED;
        } else {
            this.todoStatus = TodoStatus.COMPLETED;
        }
    }

    public void changeTodoPinned(boolean isPinned) {
        this.isPinned = isPinned;
        this.pinnedDateTime = isPinned ? LocalDateTime.now() : null;
    }

    public void addTodoEmoji(TodoEmojiEntity todoEmojiEntity) {
        todoEmojiEntities.add(todoEmojiEntity);
    }

    public boolean isContainsUserIds(List<Long> userIds) {
        return userIds.contains(userEntity.getId());
    }

    public boolean isDeleted() {
        return this.todoStatus.isDeleted();
    }
}
