package com.wanted.budget.guardian.app.web.controller.expenditure;

import com.wanted.budget.guardian.app.domain.expenditure.ExpenditureService;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureRequestDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureDetailResponseDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureIdResponseDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditurePagedResponse;
import com.wanted.budget.guardian.app.web.dto.expenditure.SearchExpenditureRequest;
import com.wanted.budget.guardian.app.web.path.ApiPath;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        @Valid @RequestBody ExpenditureRequestDto body
    ) {
        return ResponseEntity.ok(expenditureService.create(loginMember, body));
    }

    @Operation(summary = "지출 기록 수정")
    @PutMapping(ApiPath.EXPENDITURE_UPDATE)
    public ResponseEntity<Void> updateExpenditure(
        @AuthenticationPrincipal LoginMember loginMember,
        @Valid @RequestBody ExpenditureRequestDto body,
        @PathVariable Long expenditureId)
    {
        expenditureService.updateExpenditure(loginMember, body, expenditureId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "지출 합계 포함 토글")
    @PatchMapping(ApiPath.EXPENDITURE_ALLOWS_SUM_CALCULATION_TOGGLE)
    public ResponseEntity<Void> toggleExpenditureAllowsSumCalculation(
        @AuthenticationPrincipal LoginMember loginMember,
        @PathVariable Long expenditureId)
    {
        expenditureService.toggleExpenditureAllowsSumCalculation(loginMember, expenditureId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "지출 기록 삭제")
    @DeleteMapping(ApiPath.EXPENDITURE_DELETE)
    public ResponseEntity<Void> deleteExpenditure(
        @AuthenticationPrincipal LoginMember loginMember,
        @PathVariable Long expenditureId)
    {
        expenditureService.expenditureDelete(loginMember, expenditureId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "지출 목록 조회")
    @GetMapping(ApiPath.EXPENDITURE)
    public ResponseEntity<ExpenditurePagedResponse> findExpenditureBySearch(
        @AuthenticationPrincipal LoginMember loginMember,
        @Valid @ParameterObject @ModelAttribute SearchExpenditureRequest body
    ) {
        return ResponseEntity.ok(expenditureService.findExpenditureBySearch(loginMember, body));
    }

    @Operation(summary = "지출 상세 조회")
    @GetMapping(ApiPath.EXPENDITURE_FIND)
    public ResponseEntity<ExpenditureDetailResponseDto> findExpenditureDetail(
        @AuthenticationPrincipal LoginMember loginMember,
        @PathVariable Long expenditureId
    ) {
        return ResponseEntity.ok(
            expenditureService.findExpenditureDetail(loginMember, expenditureId));
    }


}
