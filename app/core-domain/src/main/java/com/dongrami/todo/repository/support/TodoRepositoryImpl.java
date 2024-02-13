package com.dongrami.todo.repository.support;

import com.dongrami.todo.domain.TodoEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.dongrami.todo.domain.QTodoEntity.todoEntity;

@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TodoEntity> findBySearch(Pageable pageable, TodoSearchDto todoSearchDto) {

        // 카운트 쿼리
        int totalCount = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(todoSearchDto))
                .fetch().size();

        // 쿼리
        JPAQuery<TodoEntity> query = queryFactory
                .select(todoEntity)
                .from(todoEntity)
                .where(searchByBuilder(todoSearchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        // 정렬
        sort(pageable, query);

        return new PageImpl<>(query.fetch(), pageable, totalCount);
    }

    /**
     * 동적으로 검색 조건을 처리하기 위한 메소드
     */
    private BooleanBuilder searchByBuilder(TodoSearchDto todoSearchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        return builder;
    }

    /**
     * 동적으로 정렬 조건을 처리하기 위한 메소드
     */
    private void sort(Pageable pageable, JPAQuery<?> query) {
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<TodoEntity> pathBuilder = new PathBuilder<>(todoEntity.getType(), todoEntity.getMetadata());

            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }
    }
}
