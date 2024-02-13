package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Comment("할일 제목")
    @Column(length = 256, nullable = false)
    private String title;

    @Comment("할일 내용")
    @Column(length = 1000, nullable = false)
    private String content;

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

    @OneToMany(mappedBy = "todoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEmojiEntity> todoEmojiEntities = new ArrayList<>();

    @OneToMany(mappedBy = "todoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoNotificationEntity> todoNotificationEntities = new ArrayList<>();

    @Builder
    public TodoEntity(Long id, String title, String content, TodoStatus todoStatus) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.todoStatus = todoStatus;
    }

    public void update(String title, String content, TodoStatus todoStatus) {
        this.title = title;
        this.content = content;
        this.todoStatus = todoStatus;
    }

}
