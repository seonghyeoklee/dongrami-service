package com.dongrami.calendar.dto;

import com.dongrami.calendar.domain.CalendarEntity;
import com.dongrami.diary.dto.DiaryDto;
import com.dongrami.todo.dto.TodoDto;
import com.dongrami.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDto {
    private Long id;
    private UserDto user;
    private TodoDto todo;
    private DiaryDto diary;
    private LocalDate calendarDate;

    public static CalendarDto from(CalendarEntity entity) {
        return CalendarDto.builder()
                .id(entity.getId())
                .user(UserDto.from(entity.getUserEntity()))
                .todo(TodoDto.from(entity.getTodoEntity()))
                .diary(DiaryDto.from(entity.getDiaryEntity()))
                .calendarDate(entity.getCalendarDate())
                .build();
    }
}
