package com.wanted.budget.guardian.common.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistUsernameException extends GeneralHttpException {

    public AlreadyExistUsernameException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 유저 이름입니다.", null);
    }
}
