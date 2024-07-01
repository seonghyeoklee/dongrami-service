package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoRememberEntity;
import com.dongrami.user.domain.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dongrami.todo.domain.QTodoRememberEntity.todoRememberEntity;

@RequiredArgsConstructor
public class TodoRememberRepositoryImpl implements TodoRememberRepositorySupport {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TodoRememberEntity> findByUserIdsAndIsDeletedFalse(UserEntity userEntity) {
        return queryFactory
                .selectFrom(todoRememberEntity)
                .where(todoRememberEntity.userEntity.in(userEntity, userEntity.getPairUserEntity()))
                .fetch();
    }
}
