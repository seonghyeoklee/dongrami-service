package com.dongrami.calendar.ui;

import com.dongrami.calendar.application.CalendarService;
import com.dongrami.calendar.dto.CalendarDto;
import com.dongrami.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/calendars")
    public ResponseEntity<?> getCalendar(@AuthenticationPrincipal User principal,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate) {
        List<CalendarDto> responses = calendarService.getCalendarByCurrentDate(principal.getUsername(), currentDate);

        return ResponseEntity.ok().body(
                ApiResponse.success(responses)
        );
    }

}
