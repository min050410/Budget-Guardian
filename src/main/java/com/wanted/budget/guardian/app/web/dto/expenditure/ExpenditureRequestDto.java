package com.wanted.budget.guardian.app.web.dto.expenditure;

import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.expenditure.Expenditure;
import com.wanted.budget.guardian.app.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExpenditureRequestDto {

    @NotNull(message = "카테고리 id를 입력해주세요")
    @Schema(description = "카테고리 id")
    private Long categoryId;

    @NotNull(message = "지출 금액을 입력해주세요")
    @Min(value = 1, message = "지출 금액은 0보다 커야 합니다.")
    @Schema(description = "지출 금액")
    private Long expense;

    @Schema(description = "메모")
    private String memo;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime expenseTime;

    public Expenditure toExpenditure(Member member, Category category) {
        return Expenditure.builder()
            .expense(expense)
            .memo(memo)
            .expenseTime(expenseTime)
            .category(category)
            .member(member)
            .build();
    }

}