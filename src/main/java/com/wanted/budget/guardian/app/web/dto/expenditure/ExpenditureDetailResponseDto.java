package com.wanted.budget.guardian.app.web.dto.expenditure;

import com.wanted.budget.guardian.app.domain.expenditure.Expenditure;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExpenditureDetailResponseDto {

    private Long expenditureId;

    private Long expense;

    private String memo;

    private boolean isIncludingExpenditure;

    private String categoryName;

    private LocalDateTime expenseTime;

    public static ExpenditureDetailResponseDto of(Expenditure expenditure) {
        return new ExpenditureDetailResponseDto(
            expenditure.getId(),
            expenditure.getExpense(),
            expenditure.getMemo(),
            expenditure.isAllowsSumCalculation(),
            expenditure.getCategory().getValue(),
            expenditure.getExpenseTime()
        );
    }

}