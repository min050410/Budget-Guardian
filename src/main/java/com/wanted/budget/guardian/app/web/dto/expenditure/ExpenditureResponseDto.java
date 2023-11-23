package com.wanted.budget.guardian.app.web.dto.expenditure;

import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.expenditure.Expenditure;
import com.wanted.budget.guardian.app.web.dto.category.CategoryTotalExpenseResponseDto;
import com.wanted.budget.guardian.common.config.response.Pagination;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExpenditureResponseDto {

    private Long expenditureId;

    private Long expense;

    private boolean isIncludingExpenditure;

    private String categoryName;

    private LocalDateTime expenseTime;

    public static ExpenditureResponseDto of(Expenditure expenditure) {
        return new ExpenditureResponseDto(
            expenditure.getId(),
            expenditure.getExpense(),
            expenditure.isAllowsSumCalculation(),
            expenditure.getCategory().getValue(),
            expenditure.getExpenseTime()
        );
    }

    public static ExpenditurePagedResponse pagedListOf(
        Pagination pagination,
        Page<Expenditure> expenditures
    ) {
        List<ExpenditureResponseDto> expenditureResponseDtoList = getExpenditureResponseDtoList(
            expenditures);
        Long totalExpense = getTotalExpense(expenditures);
        List<CategoryTotalExpenseResponseDto> categoryTotalExpenseResponseDtoList = getCategoryTotalExpenseResponseDtoList(
            expenditures);

        pagination.setTotalCount(expenditures.getTotalElements());
        pagination.setTotalPages(expenditures.getTotalPages());

        return ExpenditurePagedResponse.of(
            pagination,
            expenditureResponseDtoList,
            totalExpense,
            categoryTotalExpenseResponseDtoList
        );
    }

    private static List<ExpenditureResponseDto> getExpenditureResponseDtoList(
        Page<Expenditure> expenditures
    ) {
        return expenditures.stream()
            .map(ExpenditureResponseDto::of)
            .toList();
    }

    private static Long getTotalExpense(Page<Expenditure> expenditures) {
        return expenditures.stream()
            .mapToLong(Expenditure::getExpense).sum();
    }

    private static List<CategoryTotalExpenseResponseDto> getCategoryTotalExpenseResponseDtoList(
        Page<Expenditure> expenditures
    ) {
        Map<Category, Long> totalExpenseByCategory = expenditures.stream()
            .collect(Collectors.groupingBy(
                Expenditure::getCategory,
                Collectors.summingLong(Expenditure::getExpense)
            ));

        return CategoryTotalExpenseResponseDto.listOf(totalExpenseByCategory);
    }

}