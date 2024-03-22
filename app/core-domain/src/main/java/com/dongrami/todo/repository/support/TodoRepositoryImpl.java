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
    public Page<TodoEntity> findTodoPageByCurrentDate(Pageable pageable, LocalDate currentDate, List<Long> userIds) {
        int totalCount = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(currentDate, userIds))
                .fetch().size();

        JPAQuery<TodoEntity> query = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(currentDate, userIds))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todoEntity.pinnedDateTime.desc())
                .orderBy(todoEntity.id.asc());

        return new PageImpl<>(query.fetch(), pageable, totalCount);
    }

    @Override
    public List<TodoEntity> findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(UserEntity userEntity, LocalDate currentDate) {
        return queryFactory
                .selectFrom(todoEntity)
                .where(
                        todoEntity.userEntity.eq(userEntity)
                                .and(todoEntity.todoDate.eq(currentDate))
                                .and(todoEntity.todoStatus.ne(TodoStatus.DELETED))
                )
                .fetch();
    }

    private BooleanBuilder searchByBuilder(LocalDate currentDate, List<Long> userIds) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(todoEntity.todoStatus.ne(TodoStatus.DELETED));

        if (currentDate != null) {
            builder.and(todoEntity.todoDate.eq(currentDate));
        }

        if (userIds != null && !userIds.isEmpty()) {
            builder.and(todoEntity.userEntity.id.in(userIds));
        }

        return builder;
    }

}
