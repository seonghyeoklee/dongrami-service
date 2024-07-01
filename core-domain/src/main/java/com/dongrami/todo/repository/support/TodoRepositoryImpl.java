package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoEntity;
import com.dongrami.todo.domain.TodoStatus;
import com.dongrami.user.domain.UserEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static com.dongrami.todo.domain.QTodoEntity.todoEntity;

@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositorySupport {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TodoEntity> findTodoPageByCurrentDate(Pageable pageable, LocalDate currentDate, UserEntity userEntity) {
        int totalCount = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(currentDate, userEntity))
                .fetch().size();

        JPAQuery<TodoEntity> query = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(currentDate, userEntity))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todoEntity.pinnedDateTime.desc())
                .orderBy(todoEntity.id.asc());

        return new PageImpl<>(query.fetch(), pageable, totalCount);
    }

    @Override
    public List<TodoEntity> findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(LocalDate currentDate, UserEntity userEntity) {
        return queryFactory
                .selectFrom(todoEntity)
                .where(
                        todoEntity.userEntity.in(userEntity, userEntity.getPairUserEntity())
                                .and(todoEntity.todoDate.eq(currentDate))
                                .and(todoEntity.todoStatus.ne(TodoStatus.DELETED))
                )
                .fetch();
    }

    private BooleanBuilder searchByBuilder(LocalDate currentDate, UserEntity userEntity) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(todoEntity.todoStatus.ne(TodoStatus.DELETED));

        if (currentDate != null) {
            builder.and(todoEntity.todoDate.eq(currentDate));
        }

        if (userEntity != null) {
            builder.and(todoEntity.userEntity.in(userEntity, userEntity.getPairUserEntity()));
        }

        return builder;
    }
}
