package com.dongrami.todo.repository;

import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRememberRepository extends JpaRepository<TodoRememberEntity, Long> {

    List<TodoRememberEntity> findByUserEntityAndIsDeletedFalse(UserEntity userEntity);

}
