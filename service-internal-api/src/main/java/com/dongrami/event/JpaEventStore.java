package com.dongrami.event;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaEventStore implements EventStore {

    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;

    @Override
    public void save(Object event) {
        EventHistoryEntity eventHistoryEntity = EventHistoryEntity.builder()
                .type(event.getClass().getName())
                .payload(toJson(event))
                .build();

        eventRepository.save(eventHistoryEntity);
    }

    private String toJson(Object event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new BaseException(ErrorCode.NO_CONTENT);
        }
    }

}
