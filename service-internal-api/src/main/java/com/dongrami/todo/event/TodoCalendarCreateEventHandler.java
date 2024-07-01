package com.dongrami.todo.event;

import com.dongrami.calendar.domain.CalendarEntity;
import com.dongrami.calendar.repository.CalendarRepository;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class TodoCalendarCreateEventHandler {
    private final TodoRepository todoRepository;
    private final CalendarRepository calendarRepository;

    @Async
    @TransactionalEventListener(classes = TodoCalendarCreateEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void handle(@NotNull TodoCalendarCreateEvent event) {
        Long todoId = event.getTodoId();

        TodoEntity todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new BaseException(ErrorCode.TODO_NOT_EXIST));

        Optional<CalendarEntity> optionalCalendarEntity = calendarRepository.findByUserEntityAndCalendarDate(
                todoEntity.getAuthorUserEntity(),
                todoEntity.getTodoDate()
        );

        if (optionalCalendarEntity.isPresent()) {
            CalendarEntity calendarEntity = optionalCalendarEntity.get();
            calendarEntity.setTodoEntity(todoEntity);
        } else {
            this.createCalendar(todoEntity);
        }
    }

    private void createCalendar(TodoEntity todoEntity) {
        CalendarEntity calendarEntity = CalendarEntity.builder()
                .todoEntity(todoEntity)
                .userEntity(todoEntity.getAuthorUserEntity())
                .diaryEntity(null)
                .calendarDate(todoEntity.getTodoDate())
                .build();

        calendarRepository.save(calendarEntity);
    }

}
