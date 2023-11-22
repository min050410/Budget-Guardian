package com.wanted.budget.guardian.app.web.dto.expenditure;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExpenditureIdResponseDto {

    private Long expenditureId;

    public static ExpenditureIdResponseDto of(Long expenditureId) {
        return new ExpenditureIdResponseDto(expenditureId);
    }

}