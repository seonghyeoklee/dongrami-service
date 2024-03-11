package com.dongrami.diary.repository.support;

import com.dongrami.diary.domain.DiaryEntity;
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

import static com.dongrami.diary.domain.QDiaryEntity.diaryEntity;

@RequiredArgsConstructor
public class DiaryRepositoryImpl implements DiaryRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<DiaryEntity> findBySearch(Pageable pageable) {

        // 카운트 쿼리
        int totalCount = queryFactory
                .select(diaryEntity)
                .from(diaryEntity)
                .where(searchByBuilder())
                .fetch().size();

        // 쿼리
        JPAQuery<DiaryEntity> query = queryFactory
                .select(diaryEntity)
                .from(diaryEntity)
                .where(searchByBuilder())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        sort(pageable, query);

        return new PageImpl<>(query.fetch(), pageable, totalCount);
    }

    private BooleanBuilder searchByBuilder() {
        BooleanBuilder builder = new BooleanBuilder();

        return builder;
    }

    private void sort(Pageable pageable, JPAQuery<?> query) {
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<DiaryEntity> pathBuilder = new PathBuilder<>(diaryEntity.getType(), diaryEntity.getMetadata());

            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }
    }

}
