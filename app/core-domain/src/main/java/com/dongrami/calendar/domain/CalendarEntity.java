package com.dongrami.calendar.domain;

import com.dongrami.common.BaseTimeEntity;
import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.user.domain.UserEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendar")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CalendarEntity extends BaseTimeEntity {

    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("할 일")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", foreignKey = @ForeignKey(name = "fk_calendar_todo"))
    private TodoEntity todoEntity;

    @Comment("일기")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", foreignKey = @ForeignKey(name = "fk_calendar_diary"))
    private DiaryEntity diaryEntity;

    @Comment("사용자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_calendar_user"))
    private UserEntity userEntity;

    @Comment("일정 날짜")
    @Column(nullable = false)
    private LocalDate calendarDate;

}
