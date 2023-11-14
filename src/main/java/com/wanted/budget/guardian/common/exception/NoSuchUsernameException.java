package com.wanted.budget.guardian.common.exception;

import org.springframework.http.HttpStatus;

public class NoSuchUsernameException extends GeneralHttpException {

    public NoSuchUsernameException() {
        super(HttpStatus.NOT_FOUND, "가입되지 않은 유저네임입니다.", null);
    }

}