package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepositorySupport {

    Page<TodoEntity> findTodoPageByCurrentDate(Pageable pageable, LocalDate currentDate, List<Long> userIds);

    List<TodoEntity> findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(LocalDate currentDate, List<Long> userIds);

}
