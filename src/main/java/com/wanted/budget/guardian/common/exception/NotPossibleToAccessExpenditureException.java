package com.wanted.budget.guardian.common.exception;

import org.springframework.http.HttpStatus;

public class NotPossibleToAccessExpenditureException extends GeneralHttpException {

    public NotPossibleToAccessExpenditureException() {
        super(HttpStatus.BAD_REQUEST, "지출 기록에 접근할 수 있는 권한이 없습니다.", null);
    }

}