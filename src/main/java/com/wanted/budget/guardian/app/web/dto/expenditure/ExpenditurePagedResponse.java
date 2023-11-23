package com.wanted.budget.guardian.app.web.dto.expenditure;

import com.wanted.budget.guardian.app.web.dto.category.CategoryTotalExpenseResponseDto;
import com.wanted.budget.guardian.common.config.response.Pagination;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenditurePagedResponse {

    private Pagination pagination;

    private List<ExpenditureResponseDto> expenditures;

    private Long totalExpense;

    private List<CategoryTotalExpenseResponseDto> categoryTotalExpenses;

    public static ExpenditurePagedResponse of(
        Pagination pagination,
        List<ExpenditureResponseDto> expenditures,
        Long totalExpense,
        List<CategoryTotalExpenseResponseDto> categoryTotalExpenses
    ) {
        return new ExpenditurePagedResponse(
            pagination,
            expenditures,
            totalExpense,
            categoryTotalExpenses
        );
    }

}
