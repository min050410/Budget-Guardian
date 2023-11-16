package com.wanted.budget.guardian.app.web.controller.budget;

import com.wanted.budget.guardian.app.web.dto.budget.BudgetIdResponseDto;
import com.wanted.budget.guardian.app.web.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class BudgetController {

    @Operation(summary = "예산 설정")
    @GetMapping(ApiPath.BUDGET)
    public ResponseEntity<BudgetIdResponseDto> createBudget() {
//        return ResponseEntity.ok();
    }

}
