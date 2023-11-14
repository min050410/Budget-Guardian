package com.wanted.budget.guardian.app.web.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccessTokenResponseDto {

    private String accessToken;

    private String accessTokenValidity;

    public static AccessTokenResponseDto of(String accessToken, String accessTokenValidity) {
        return new AccessTokenResponseDto(
            accessToken,
            accessTokenValidity
        );
    }

}