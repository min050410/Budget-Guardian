package com.wanted.budget.guardian.app.web.controller.auth;

import com.wanted.budget.guardian.app.domain.auth.AuthService;
import com.wanted.budget.guardian.app.web.dto.auth.AccessTokenResponseDto;
import com.wanted.budget.guardian.app.web.dto.auth.LoginRequestDto;
import com.wanted.budget.guardian.app.web.dto.auth.RefreshTokenRequestDto;
import com.wanted.budget.guardian.app.web.dto.auth.SignUpRequestDto;
import com.wanted.budget.guardian.app.web.dto.auth.TokenResponseDto;
import com.wanted.budget.guardian.app.web.dto.member.MemberIdResponseDto;
import com.wanted.budget.guardian.app.web.path.ApiPath;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "멤버 회원가입")
    @PostMapping(ApiPath.SIGNUP)
    public ResponseEntity<MemberIdResponseDto> signup(
        @Valid @RequestBody SignUpRequestDto body
    ) {
        return ResponseEntity.ok(authService.signUp(body));
    }

    @Operation(summary = "멤버 로그인")
    @PostMapping(ApiPath.LOGIN)
    public ResponseEntity<TokenResponseDto> login(
        @Valid @RequestBody LoginRequestDto body) {
        return ResponseEntity.ok(authService.generateAccessAndRefreshToken(body));
    }

}