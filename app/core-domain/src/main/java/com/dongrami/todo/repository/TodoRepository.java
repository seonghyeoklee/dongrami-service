package com.dongrami.todo.repository;

import com.dongrami.todo.domain.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
