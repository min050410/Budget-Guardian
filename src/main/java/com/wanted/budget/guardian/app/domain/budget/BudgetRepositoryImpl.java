package com.wanted.budget.guardian.app.domain.budget;

import static com.wanted.budget.guardian.app.domain.budget.QBudget.budget;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.member.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Override
    public Map<Category, Double> getAveragePerOfBudgetPerCategory() {
        List<Tuple> averagePerOfBudgetPerCategoryTuples = jpaQueryFactory
            .select(budget.category, budget.perOfBudgetAmount.avg())
            .from(budget)
            .where(
                createDateMonthEq(budget.createDate)
            )
            .groupBy(budget.category)
            .fetch();

        return averagePerOfBudgetPerCategoryTuples.stream()
            .collect(Collectors.toMap(
                tuple -> tuple.get(budget.category),
                tuple -> Optional.ofNullable(tuple.get(budget.perOfBudgetAmount.avg())).orElse(0D)
            ));
    }

}