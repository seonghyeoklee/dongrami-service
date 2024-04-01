package com.dongrami.calendar.repository;

import com.dongrami.calendar.domain.CalendarEntity;
import com.dongrami.calendar.repository.support.CalendarRepositorySupport;
import com.dongrami.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long>, CalendarRepositorySupport {

    Optional<CalendarEntity> findByUserEntityAndCalendarDate(UserEntity userEntity, LocalDate calendarDate);

}
