package com.wanted.budget.guardian.app.web.controller.budget;

import com.wanted.budget.guardian.app.domain.budget.BudgetService;
import com.wanted.budget.guardian.app.web.dto.budget.CreateBudgetRequestDto;
import com.wanted.budget.guardian.app.web.path.ApiPath;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "예산")
@RequiredArgsConstructor
@RestController
public class BudgetController {

    private final BudgetService budgetService;

    @Operation(summary = "예산 설정")
    @PostMapping(ApiPath.BUDGET)
    public ResponseEntity<Void> configureBudget(
        @AuthenticationPrincipal LoginMember loginMember,
        @Valid @RequestBody CreateBudgetRequestDto body
    ) {
        budgetService.configureBudget(loginMember, body);
        return ResponseEntity.ok().build();
    }

}
