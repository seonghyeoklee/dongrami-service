package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo_notification")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoNotificationEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("할일 알림 시간")
    @Column(nullable = false)
    private LocalDateTime notificationDateTime;

    @Comment("할일")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", foreignKey = @ForeignKey(name = "fk_todo_notification_todo"))
    private TodoEntity todoEntity;

}
