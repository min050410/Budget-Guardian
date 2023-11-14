package com.wanted.budget.guardian.app.web.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TokenResponseDto {

    private String accessToken;

    private String refreshToken;

    private String accessTokenValidity;

    private String refreshTokenValidity;

    public static TokenResponseDto of(AccessTokenResponseDto accessTokenResponseDto,
        RefreshTokenResponseDto refreshTokenResponseDto) {
        return new TokenResponseDto(
            accessTokenResponseDto.getAccessToken(),
            refreshTokenResponseDto.getRefreshToken(),
            accessTokenResponseDto.getAccessTokenValidity(),
            refreshTokenResponseDto.getRefreshTokenValidity()
        );
    }

}