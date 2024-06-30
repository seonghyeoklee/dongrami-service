package com.dongrami.calendar.repository.support;

import com.dongrami.calendar.domain.CalendarEntity;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepositorySupport {

    List<CalendarEntity> findAllByUserEntityAndCalendarDate(Long userId, LocalDate currentDate);

}
