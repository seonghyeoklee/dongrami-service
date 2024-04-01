package com.dongrami.diary.event;

import com.dongrami.calendar.domain.CalendarEntity;
import com.dongrami.calendar.repository.CalendarRepository;
import com.dongrami.diary.domain.DiaryEntity;
import com.dongrami.diary.repository.DiaryRepository;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
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
public class DiaryCalendarCreateEventHandler {
    private final DiaryRepository diaryRepository;
    private final CalendarRepository calendarRepository;

    @Async
    @TransactionalEventListener(classes = DiaryCalendarCreateEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void handle(@NotNull DiaryCalendarCreateEvent event) {
        Long diaryId = event.getDiaryId();

        DiaryEntity diaryEntity = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new BaseException(ErrorCode.DIARY_NOT_EXIST));

        Optional<CalendarEntity> optionalCalendarEntity = calendarRepository.findByUserEntityAndCalendarDate(
                diaryEntity.getUserEntity(),
                diaryEntity.getWrittenDate()
        );

        if (optionalCalendarEntity.isPresent()) {
            CalendarEntity calendarEntity = optionalCalendarEntity.get();
            calendarEntity.setDiaryEntity(diaryEntity);
        } else {
            this.createCalendar(diaryEntity);
        }
    }

    private void createCalendar(DiaryEntity diaryEntity) {
        CalendarEntity calendarEntity = CalendarEntity.builder()
                .todoEntity(null)
                .userEntity(diaryEntity.getUserEntity())
                .diaryEntity(diaryEntity)
                .calendarDate(diaryEntity.getWrittenDate())
                .build();

        calendarRepository.save(calendarEntity);
    }

}
