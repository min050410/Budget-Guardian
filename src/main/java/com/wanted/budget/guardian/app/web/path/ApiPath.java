package com.wanted.budget.guardian.app.web.path;

public class ApiPath {

    // 에러 핸들러
    public static final String ERROR_AUTH = "/api/v1/error";

    // 인증
    public static final String SIGNUP = "/api/v1/signup";
    public static final String LOGIN = "/api/v1/login";
    public static final String REFRESH_TOKEN = "/api/v1/oauth/refresh";
    public static final String VALIDATE_TOKEN = "/api/v1/oauth/validate";
    public static final String LOGOUT = "/api/v1/logout";

    // 카테고리
    public static final String CATEGORY = "/api/v1/category";

    // 예산
    public static final String BUDGET = "/api/v1/budget";
    public static final String BUDGET_RECOMMEND = "/api/v1/budget/recommend";

    // 지출
    public static final String EXPENDITURE = "/api/v1/expenditure";
    public static final String EXPENDITURE_FIND = "/api/v1/expenditure/{expenditureId}";
    public static final String EXPENDITURE_UPDATE = "/api/v1/expenditure/{expenditureId}";
    public static final String EXPENDITURE_DELETE = "/api/v1/expenditure/{expenditureId}";
    public static final String EXPENDITURE_ALLOWS_SUM_CALCULATION_TOGGLE = "/api/v1/expenditure/calculation/allows/{expenditureId}";

}
