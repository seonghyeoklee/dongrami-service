package com.dongrami.calendar.repository.support;

import com.dongrami.calendar.domain.CalendarEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.dongrami.calendar.domain.QCalendarEntity.calendarEntity;

@RequiredArgsConstructor
public class CalendarRepositoryImpl implements CalendarRepositorySupport {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CalendarEntity> findAllByUserEntityAndCalendarDate(Long userId, LocalDate currentDate) {
        return queryFactory
                .select(calendarEntity)
                .from(calendarEntity)
                .where(searchByBuilder(userId, currentDate))
                .orderBy(calendarEntity.calendarDate.asc())
                .orderBy(calendarEntity.id.asc())
                .fetch();
    }

    private BooleanExpression searchByBuilder(Long userId, LocalDate currentDate) {
        LocalDate startDate = currentDate.withDayOfMonth(1);
        LocalDate endDate = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

        return (
                calendarEntity.userEntity.pairUserEntity.id.eq(userId)
                        .and(calendarEntity.diaryEntity.isPublic.isTrue())
                        .or(calendarEntity.userEntity.id.eq(userId))
        )
                .and(calendarEntity.calendarDate.between(startDate, endDate));
    }

}
