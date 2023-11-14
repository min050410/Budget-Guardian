package com.wanted.budget.guardian.common.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("spring.security.jwt")
public class JwtProperties {

    private final String header;

    private final String signKey;

    private final Long accessTokenValidityInSeconds;

    private final Long refreshTokenValidityInSeconds;


}
