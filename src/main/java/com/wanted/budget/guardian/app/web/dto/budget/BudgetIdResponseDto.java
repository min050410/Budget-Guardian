package com.wanted.budget.guardian.app.web.dto.budget;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BudgetIdResponseDto {

    private Long budgetId;

    public static BudgetIdResponseDto of(Long budgetId) {
        return new BudgetIdResponseDto(budgetId);
    }

}