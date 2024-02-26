package com.dongrami.todo.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @Comment("할일 시작 날짜")
    @Column(nullable = false)
    private LocalDate startDate;

    @Comment("할일 종료 날짜")
    @Column(nullable = false)
    private LocalDate endDate;

    @Comment("할일 제외 날짜")
    @Column
    @Convert(converter = LocalDateListConverter.class)
    private List<LocalDate> excludedDates;

    @Comment("할일 알림 시간")
    @Column(nullable = false)
    private LocalTime notificationTime;

    @Setter
    @Comment("할일")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", foreignKey = @ForeignKey(name = "fk_todo_notification_todo"))
    private TodoEntity todoEntity;

    @Comment("알림을 설정한 사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_todo_notification_user"))
    private UserEntity userEntity;

}
