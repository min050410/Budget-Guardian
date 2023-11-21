package com.wanted.budget.guardian.app.domain.budget;

import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.member.Member;
import java.util.Map;

public interface BudgetRepositoryCustom {

    Budget findDuplicateBudget(Member member, Category category);

    Map<Category, Double> getAveragePerOfBudgetPerCategory();

}
