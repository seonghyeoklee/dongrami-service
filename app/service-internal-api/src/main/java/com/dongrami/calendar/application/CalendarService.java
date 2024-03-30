package com.dongrami.calendar.application;

import com.dongrami.calendar.domain.CalendarEntity;
import com.dongrami.calendar.dto.CalendarDto;
import com.dongrami.calendar.repository.CalendarRepository;
import com.dongrami.user.application.UserService;
import com.dongrami.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {
    private final UserService userService;
    private final CalendarRepository calendarRepository;

    public List<CalendarDto> getCalendarByCurrentDate(String username, LocalDate currentDate) {
        UserEntity userEntity = userService.getUserByUserUniqueId(username);

        List<CalendarEntity> calendarEntities = calendarRepository.findAllByUserEntityAndAndCalendarDate(userEntity, currentDate);

        return calendarEntities.stream()
                .map(CalendarDto::from)
                .toList();
    }

}
