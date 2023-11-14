package com.wanted.budget.guardian.common.config.security;


import static com.wanted.budget.guardian.common.config.security.jwt.JwtConstants.JWT_EXCEPTION;

import com.wanted.budget.guardian.app.web.path.ApiPath;
import com.wanted.budget.guardian.common.config.security.jwt.JwtExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${budget-guardian.host}")
    private String host;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        JwtExceptionCode exceptionCode = (JwtExceptionCode) request.getAttribute(JWT_EXCEPTION);
        String encode = URLEncoder.encode("비정상적인 접근입니다.", StandardCharsets.UTF_8);

        if (Objects.nonNull(exceptionCode)) {
            encode = URLEncoder.encode(exceptionCode.getMessage(), StandardCharsets.UTF_8);
        }
        response.sendRedirect(host + ApiPath.ERROR_AUTH + "?message=" + encode);
    }

}
