package com.dongrami.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventStoreHandler {

    private final EventStore eventStore;

    @EventListener(Event.class)
    public void handle(Event event) {
        log.info(">>> Event Execute {}", event.getClass().getName());
        eventStore.save(event);
    }

}
