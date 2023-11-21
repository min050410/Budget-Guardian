package com.wanted.budget.guardian.app.web.dto.budget;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CreateBudgetRequestDto {

    @NotEmpty(message = "카테고리 별 예산을 입력해주세요")
    @Schema(description = "카테고리 별 예산")
    private List<
        @NotNull(message = "카테고리 별 예산을 입력해주세요")
            CategoryByBudgetRequestDto> categoryByBudgets;

}