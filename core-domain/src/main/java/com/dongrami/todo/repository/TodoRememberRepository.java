package com.dongrami.todo.repository;

import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.todo.repository.support.TodoRememberRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRememberRepository extends JpaRepository<TodoRememberEntity, Long>, TodoRememberRepositorySupport {
}
