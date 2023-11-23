package com.wanted.budget.guardian.app.domain.expenditure;

import static com.wanted.budget.guardian.app.domain.expenditure.QExpenditure.expenditure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.budget.guardian.app.domain.member.Member;
import com.wanted.budget.guardian.app.web.dto.expenditure.SearchExpenditureRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ExpenditureRepositoryImpl implements ExpenditureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Expenditure> findExpenditureListBySearch(
        SearchExpenditureRequest filter,
        Member member,
        Pageable pageable
    ) {
        List<Expenditure> contents = jpaQueryFactory
            .selectFrom(expenditure)
            .where(
                memberEq(member),
                categoryEq(filter.getCategoryId()),
                expenseTimeMonthEq(expenditure.expenseTime),
                expenseRange(filter.getMinExpense(), filter.getMaxExpense())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Expenditure> countQuery = jpaQueryFactory
            .selectFrom(expenditure)
            .where(
                categoryEq(filter.getCategoryId()),
                expenseRange(filter.getMinExpense(), filter.getMaxExpense())
            );

        return PageableExecutionUtils.getPage(contents, pageable, () -> countQuery.fetch().size());
    }

    private BooleanExpression memberEq(Member member) {
        return expenditure.member.eq(member);
    }

    private BooleanExpression categoryEq(Long categoryId) {
        if (categoryId == null) {
            return Expressions.TRUE;
        }

        return expenditure.category.id.eq(categoryId);
    }

    private BooleanExpression expenseTimeMonthEq(DateTimePath<LocalDateTime> expenseTime) {
        LocalDate currentDate = new java.sql.Date(System.currentTimeMillis()).toLocalDate();
        return expenseTime.year().eq(currentDate.getYear())
            .and(expenseTime.month().eq(currentDate.getMonthValue()));
    }

    private BooleanExpression expenseRange(Long minExpense, Long maxExpense) {
        if (minExpense == null || maxExpense == null) {
            return Expressions.TRUE;
        }

        return expenditure.expense.goe(minExpense)
            .and(expenditure.expense.loe(maxExpense));
    }

}
