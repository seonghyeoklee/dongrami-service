package com.dongrami.diary.event;

import com.dongrami.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryCalendarCreateEvent extends Event {
    private final Long diaryId;
}
