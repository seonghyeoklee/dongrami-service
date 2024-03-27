package com.dongrami.event;

public interface EventStore {

    void save(Object event);

}
