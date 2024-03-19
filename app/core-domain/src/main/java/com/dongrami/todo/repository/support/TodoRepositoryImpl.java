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

    private BooleanBuilder searchByBuilder(TodoSearchDto todoSearchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (todoSearchDto.getCurrentDate() != null) {
            builder.and(todoEntity.todoDate.eq(todoSearchDto.getCurrentDate()));
        }

        if (todoSearchDto.getIsDeleted()) {
            builder.and(todoEntity.todoStatus.eq(TodoStatus.DELETED));
        } else {
            builder.and(todoEntity.todoStatus.ne(TodoStatus.DELETED));
        }

        if (todoSearchDto.getIsPinned() != null) {
            if (todoSearchDto.getIsPinned()) {
                builder.and(todoEntity.isPinned.isTrue());
            } else {
                builder.and(todoEntity.isPinned.isFalse());
            }
        }

        return builder;
    }

}
