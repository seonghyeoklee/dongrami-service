package com.dongrami.diary.repository.support;

import com.dongrami.diary.domain.DiaryEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static com.dongrami.diary.domain.QDiaryEntity.diaryEntity;
import static com.dongrami.user.domain.QUserEntity.userEntity;

@RequiredArgsConstructor
public class DiaryRepositoryImpl implements DiaryRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<DiaryEntity> findDiaryPageByCurrentDate(Long userId, Pageable pageable, LocalDate currentDate) {
        int totalCount = queryFactory
                .select(diaryEntity)
                .from(diaryEntity)
                .where(searchByBuilder(userId, currentDate))
                .fetch().size();

        JPAQuery<DiaryEntity> query = queryFactory
                .select(diaryEntity)
                .from(diaryEntity)
                .where(searchByBuilder(userId, currentDate))
                .orderBy(diaryEntity.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return new PageImpl<>(query.fetch(), pageable, totalCount);
    }

    private BooleanExpression searchByBuilder(Long userId, LocalDate currentDate) {
        LocalDate startDate = currentDate.withDayOfMonth(1);
        LocalDate endDate = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

        return (
                diaryEntity.userEntity.id.in(
                                JPAExpressions.select(userEntity.id)
                                        .from(userEntity)
                                        .where(userEntity.userGroupEntity.id.in(
                                                        JPAExpressions.select(userEntity.userGroupEntity.id)
                                                                .from(userEntity)
                                                                .where(userEntity.id.eq(userId))
                                                )
                                        )
                        )
                        .and(diaryEntity.isPublic.eq(true))
                        .or(diaryEntity.userEntity.id.eq(userId))
        )
                .and(diaryEntity.isDeleted.eq(false))
                .and(diaryEntity.writtenDate.between(startDate, endDate));
    }

}
