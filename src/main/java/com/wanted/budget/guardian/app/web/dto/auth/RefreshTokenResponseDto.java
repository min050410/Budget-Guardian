package com.wanted.budget.guardian.app.web.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RefreshTokenResponseDto {

    private String refreshToken;

    private String refreshTokenValidity;

    public static RefreshTokenResponseDto of(String refreshToken, String refreshTokenValidity) {
        return new RefreshTokenResponseDto(
            refreshToken,
            refreshTokenValidity
        );
    }

}