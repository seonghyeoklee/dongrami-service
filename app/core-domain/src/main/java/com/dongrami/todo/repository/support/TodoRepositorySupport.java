package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoRepositorySupport {

    Page<TodoEntity> findBySearch(Pageable pageable, TodoSearchDto todoSearchDto);

}
