package com.dongrami.todo.event;

import com.dongrami.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoCalendarCreateEvent extends Event {
    private final Long todoId;
}
