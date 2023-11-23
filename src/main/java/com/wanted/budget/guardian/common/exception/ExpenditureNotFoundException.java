package com.wanted.budget.guardian.common.exception;

import org.springframework.http.HttpStatus;

public class ExpenditureNotFoundException extends GeneralHttpException {

    public ExpenditureNotFoundException() {
        super(HttpStatus.NOT_FOUND, "지출 기록을 찾을 수 없습니다.", null);
    }

}