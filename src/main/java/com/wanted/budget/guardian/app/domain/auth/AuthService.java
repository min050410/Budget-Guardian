package com.wanted.budget.guardian.app.domain.auth;

import com.wanted.budget.guardian.app.domain.member.Member;
import com.wanted.budget.guardian.app.domain.member.MemberRepository;
import com.wanted.budget.guardian.app.domain.member.MemberService;
import com.wanted.budget.guardian.app.web.dto.auth.AccessTokenResponseDto;
import com.wanted.budget.guardian.app.web.dto.auth.LoginRequestDto;
import com.wanted.budget.guardian.app.web.dto.auth.RefreshTokenRequestDto;
import com.wanted.budget.guardian.app.web.dto.auth.SignUpRequestDto;
import com.wanted.budget.guardian.app.web.dto.auth.TokenResponseDto;
import com.wanted.budget.guardian.app.web.dto.member.MemberIdResponseDto;
import com.wanted.budget.guardian.common.config.redis.RefreshToken;
import com.wanted.budget.guardian.common.config.security.jwt.JwtTokenFactory;
import com.wanted.budget.guardian.common.exception.AlreadyExistUsernameException;
import com.wanted.budget.guardian.common.exception.MemberNotFoundException;
import com.wanted.budget.guardian.common.exception.NoSuchUsernameException;
import com.wanted.budget.guardian.common.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final JwtTokenFactory jwtTokenFactory;

    private final PasswordEncoder passwordEncoder;

    /**
     * 유저 회원 가입
     */
    public MemberIdResponseDto signUp(SignUpRequestDto body) {
        if (memberRepository.isPresentUserByUsername(body.getUsername())) {
            throw new AlreadyExistUsernameException();
        }

        if (!body.getPassword().equals(body.getCheckedPassword())) {
            throw new PasswordNotMatchException();
        }

        return memberService.create(body);
    }

}
