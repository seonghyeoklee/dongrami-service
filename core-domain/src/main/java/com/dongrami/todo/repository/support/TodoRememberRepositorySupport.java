package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoRememberEntity;

import java.util.List;

public interface TodoRememberRepositorySupport {

    List<TodoRememberEntity> findByUserIdsAndIsDeletedFalse(List<Long> userIds);

}
