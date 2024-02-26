package com.dongrami.todo.repository;

import com.dongrami.todo.domain.TodoNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoNotificationRepository extends JpaRepository<TodoNotificationEntity, Long> {
}
