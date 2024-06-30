package com.dongrami.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventHistoryEntity, Long> {
}
