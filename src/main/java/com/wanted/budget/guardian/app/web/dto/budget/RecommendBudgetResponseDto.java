package com.wanted.budget.guardian.app.web.dto.budget;

import com.wanted.budget.guardian.app.domain.category.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RecommendBudgetResponseDto {

    private Long categoryId;

    private Long budgetAmount;

    public static RecommendBudgetResponseDto of(Long categoryId, Long budgetAmount) {
        return new RecommendBudgetResponseDto(
            categoryId,
            budgetAmount
        );
    }

    public static List<RecommendBudgetResponseDto> listOf(Long totalBudget,
        Map<Category, Double> averagePerOfBudgetPerCategory) {
        List<RecommendBudgetResponseDto> recommendBudgetResponseDtoList = new ArrayList<>();

        for (Map.Entry<Category, Double> entry : averagePerOfBudgetPerCategory.entrySet()) {
            recommendBudgetResponseDtoList.add(
                calculateBudgetForCategory(totalBudget, entry.getKey(), entry.getValue()));
        }
        return recommendBudgetResponseDtoList;
    }

    private static RecommendBudgetResponseDto calculateBudgetForCategory(
        Long totalBudget, Category category, Double averagePerOfBudget) {
        double budgetAmount = totalBudget * (averagePerOfBudget / 100);
        return RecommendBudgetResponseDto.of(category.getId(), Math.round(budgetAmount));
    }

}