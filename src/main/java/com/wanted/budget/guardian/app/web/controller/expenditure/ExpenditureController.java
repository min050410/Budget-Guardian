package com.wanted.budget.guardian.app.web.controller.expenditure;

import com.wanted.budget.guardian.app.domain.expenditure.ExpenditureService;
import com.wanted.budget.guardian.app.web.dto.expenditure.CreateExpenditureRequestDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureIdResponseDto;
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

@Tag(name = "지출")
@RequiredArgsConstructor
@RestController
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    @Operation(summary = "지출 기록 생성")
    @PostMapping(ApiPath.EXPENDITURE)
    public ResponseEntity<ExpenditureIdResponseDto> createExpenditure(
        @AuthenticationPrincipal LoginMember loginMember,
        @Valid @RequestBody CreateExpenditureRequestDto body
    ) {
        return ResponseEntity.ok(expenditureService.create(loginMember, body));
    }

}
