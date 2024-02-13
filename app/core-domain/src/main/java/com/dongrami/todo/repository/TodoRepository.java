package com.dongrami.todo.repository;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.repository.support.TodoRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoRepositorySupport {
}
