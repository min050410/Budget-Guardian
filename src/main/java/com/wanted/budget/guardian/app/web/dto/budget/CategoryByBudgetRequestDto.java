package com.wanted.budget.guardian.app.web.dto.budget;

import com.wanted.budget.guardian.app.domain.budget.Budget;
import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryByBudgetRequestDto {

    @NotNull(message = "카테고리 id를 입력해주세요")
    @Schema(description = "카테고리 id")
    private Long categoryId;

    @NotNull(message = "예산을 입력해주세요")
    @Min(value = 1, message = "예산은 0보다 커야 합니다.")
    @Schema(description = "예산")
    private Long budgetAmount;

    public Budget toBudget(Category category, Member member, Long perOfBudgetAmount) {
        return Budget.builder()
            .budgetAmount(budgetAmount)
            .perOfBudgetAmount(perOfBudgetAmount)
            .member(member)
            .category(category)
            .build();
    }

}