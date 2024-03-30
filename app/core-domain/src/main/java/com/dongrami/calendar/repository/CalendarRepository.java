package com.dongrami.calendar.repository;

import com.dongrami.calendar.domain.CalendarEntity;
import com.dongrami.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    List<CalendarEntity> findAllByUserEntityAndAndCalendarDate(UserEntity userEntity, LocalDate calendarDate);

}
