package com.wanted.budget.guardian.common.config.security;

import com.wanted.budget.guardian.app.web.path.ApiPath;
import com.wanted.budget.guardian.common.config.security.jwt.CustomAccessDeniedHandler;
import com.wanted.budget.guardian.common.config.security.jwt.JwtOncePerRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtOncePerRequestFilter jwtOncePerRequestFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] WHITE_LIST = {
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/resources/**",
        "/"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)

            .sessionManagement((session) ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .headers((header) ->
                header.frameOptions(
                    HeadersConfigurer.FrameOptionsConfig::sameOrigin
                )
            )

            .authorizeHttpRequests((registry) ->
                registry.requestMatchers(WHITE_LIST).permitAll()
                    // 인증
                    .requestMatchers(ApiPath.SIGNUP, ApiPath.LOGIN, ApiPath.REFRESH_TOKEN).permitAll()

                    // 에러 핸들러
                    .requestMatchers(ApiPath.ERROR_AUTH).permitAll()
                    .anyRequest().authenticated()
            )

            .addFilterBefore(jwtOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling((handling) ->
                handling.authenticationEntryPoint(customAuthenticationEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler)
            )
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}