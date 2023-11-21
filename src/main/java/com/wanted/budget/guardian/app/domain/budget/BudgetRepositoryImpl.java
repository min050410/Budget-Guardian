package com.wanted.budget.guardian.app.domain.budget;

import static com.wanted.budget.guardian.app.domain.budget.QBudget.budget;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.member.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BudgetRepositoryImpl implements BudgetRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Budget findDuplicateBudget(Member member, Category category) {
        return jpaQueryFactory
            .selectFrom(budget)
            .where(
                budget.member.eq(member),
                budget.category.eq(category),
                createDateMonthEq(budget.createDate)
            )
            .fetchOne();
    }

    private BooleanExpression createDateMonthEq(DateTimePath<LocalDateTime> createDate) {
        LocalDate currentDate = new java.sql.Date(System.currentTimeMillis()).toLocalDate();
        return createDate.year().eq(currentDate.getYear())
            .and(createDate.month().eq(currentDate.getMonthValue()));
    }

}