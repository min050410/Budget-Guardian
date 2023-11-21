package com.wanted.budget.guardian.common.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends GeneralHttpException {

    public CategoryNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 카테고리는 존재하지 않습니다.", null);
    }

}