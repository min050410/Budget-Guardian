package com.wanted.budget.guardian.app.web.dto.budget;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RecommendBudgetRequestDto {

    @NotNull(message = "총 예산을 입력해주세요")
    @Schema(description = "총 예산")
    private Long totalBudget;

}