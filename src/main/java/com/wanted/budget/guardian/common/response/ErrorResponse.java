package com.wanted.budget.guardian.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private Integer statusCode;
    private String reason;
    private String message;

}