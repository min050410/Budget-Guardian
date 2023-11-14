package com.wanted.budget.guardian.common.exception;

import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends GeneralHttpException {

    public RefreshTokenNotFoundException() {
        super(HttpStatus.NOT_FOUND, "리프레시 토큰이 만료되었거나 사용할 수 없습니다.", null);
    }

}