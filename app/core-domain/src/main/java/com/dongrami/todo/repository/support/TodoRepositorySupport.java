package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.user.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepositorySupport {

    Page<TodoEntity> findBySearch(Pageable pageable, TodoSearchDto todoSearchDto);

    List<TodoEntity> findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(UserEntity userEntity, LocalDateTime from, LocalDateTime to);

}
