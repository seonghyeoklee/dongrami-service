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

import java.time.LocalDateTime;
import java.util.List;

import static com.dongrami.todo.domain.QTodoEntity.todoEntity;

@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TodoEntity> findBySearch(Pageable pageable, TodoSearchDto todoSearchDto) {
        int totalCount = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(todoSearchDto))
                .fetch().size();

        JPAQuery<TodoEntity> query = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(todoSearchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todoEntity.pinnedDateTime.desc())
                .orderBy(todoEntity.id.desc());

        return new PageImpl<>(query.fetch(), pageable, totalCount);
    }

    @Override
    public List<TodoEntity> findByUserEntityAndCreatedDateTimeAndIsDeletedFalse(UserEntity userEntity, LocalDateTime from, LocalDateTime to) {
        return queryFactory
                .selectFrom(todoEntity)
                .where(
                        todoEntity.userEntity.eq(userEntity)
                                .and(todoEntity.createdDateTime.between(from, to))
                                .and(todoEntity.todoStatus.ne(TodoStatus.DELETED))
                )
                .fetch();
    }

    private BooleanBuilder searchByBuilder(TodoSearchDto todoSearchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (todoSearchDto.getCurrentDate() != null) {
            builder.and(todoEntity.todoDate.eq(todoSearchDto.getCurrentDate()));
        }

        return builder;
    }

}
