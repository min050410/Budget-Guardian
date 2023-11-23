package com.wanted.budget.guardian.app.web.dto.expenditure;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SearchExpenditureRequest {

    private Long categoryId;

    private Long minExpense;

    private Long maxExpense;

    private Integer size;

    private Integer page;

}
