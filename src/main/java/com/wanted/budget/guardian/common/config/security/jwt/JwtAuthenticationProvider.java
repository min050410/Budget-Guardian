package com.wanted.budget.guardian.common.config.security.jwt;

import static com.wanted.budget.guardian.common.config.security.jwt.JwtConstants.ID;
import static com.wanted.budget.guardian.common.config.security.jwt.JwtConstants.MEMBER_USERNAME;

import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider {

    private final JwtTokenFactory jwtTokenFactory;

    public Authentication authenticate(String token) throws AuthenticationException {
        Claims claims = jwtTokenFactory.parseClaims(token);

        Long id = claims.get(ID, Long.class);
        String username = claims.get(MEMBER_USERNAME, String.class);
        LoginMember loginMember = LoginMember.of(id, username);

        return new UsernamePasswordAuthenticationToken(loginMember, null, null);
    }

}
