package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.user.domain.UserEntity;

import java.util.List;

public interface TodoRememberRepositorySupport {

    List<TodoRememberEntity> findByUserIdsAndIsDeletedFalse(UserEntity userEntity);
}
