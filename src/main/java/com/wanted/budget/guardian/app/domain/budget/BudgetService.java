package com.wanted.budget.guardian.app.domain.budget;

import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.category.CategoryService;
import com.wanted.budget.guardian.app.domain.member.Member;
import com.wanted.budget.guardian.app.domain.member.MemberService;
import com.wanted.budget.guardian.app.web.dto.budget.CategoryByBudgetRequestDto;
import com.wanted.budget.guardian.app.web.dto.budget.CreateBudgetRequestDto;
import com.wanted.budget.guardian.app.web.dto.budget.RecommendBudgetRequestDto;
import com.wanted.budget.guardian.app.web.dto.budget.RecommendBudgetResponseDto;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import com.wanted.budget.guardian.common.response.ListResponse;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;

    private final CategoryService categoryService;

    private final MemberService memberService;

    /**
     * 예산 설정
     */
    public void configureBudget(LoginMember loginMember, CreateBudgetRequestDto body) {
        Member member = memberService.findLoginMember(loginMember);
        List<CategoryByBudgetRequestDto> categoryByBudgets = body.getCategoryByBudgets();
        Long totalAmount = getTotalAmount(categoryByBudgets);

        categoryByBudgets.forEach(
            categoryByBudget -> upsertBudget(member, totalAmount, categoryByBudget));
    }

    private Long getTotalAmount(List<CategoryByBudgetRequestDto> categoryByBudgets) {
        return categoryByBudgets.stream()
            .mapToLong(CategoryByBudgetRequestDto::getBudgetAmount).sum();
    }

    @Transactional
    public void upsertBudget(Member member, Long totalAmount, CategoryByBudgetRequestDto body) {
        Category category = categoryService.findById(body.getCategoryId());
        Long budgetAmount = body.getBudgetAmount();
        Long perOfBudgetAmount = getPerOfBudgetAmount(budgetAmount, totalAmount);

        Budget existingBudget = budgetRepository.findDuplicateBudget(member, category);
        if (existingBudget != null) {
            existingBudget.updateBudgetAmount(budgetAmount, perOfBudgetAmount);
            budgetRepository.save(existingBudget);
            return;
        }
        Budget budget = body.toBudget(category, member, perOfBudgetAmount);
        budgetRepository.save(budget);
    }

    private Long getPerOfBudgetAmount(Long budgetAmount, Long totalAmount) {
        return budgetAmount * 100L / totalAmount;
    }

    /**
     * 예산 추천
     */
    public ListResponse<RecommendBudgetResponseDto> recommendBudget(RecommendBudgetRequestDto body) {
        Long totalBudget = body.getTotalBudget();

        Map<Category, Double> averagePerOfBudgetPerCategory = budgetRepository.getAveragePerOfBudgetPerCategory();

        List<RecommendBudgetResponseDto> RecommendBudgetResponseDtoList = RecommendBudgetResponseDto.listOf(totalBudget, averagePerOfBudgetPerCategory);
        return ListResponse.of(RecommendBudgetResponseDtoList);
    }

}
