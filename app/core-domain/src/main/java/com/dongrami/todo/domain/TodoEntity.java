package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256)
    private String title;

    @Column(length = 1000)
    private String content;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @Column
    private LocalDateTime alarmDateTime;

    @Builder
    public TodoEntity(Long id, String title, String content, TodoStatus todoStatus, LocalDateTime alarmDateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.todoStatus = todoStatus;
        this.alarmDateTime = alarmDateTime;
    }

    public void update(String title, String content, TodoStatus todoStatus, LocalDateTime alarmDateTime) {
        this.title = title;
        this.content = content;
        this.todoStatus = todoStatus;
        this.alarmDateTime = alarmDateTime;
    }
}
