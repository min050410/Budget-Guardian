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

    /**
     * 액세스 토큰, 리프레시 토큰 생성
     */
    @Transactional
    public TokenResponseDto generateAccessAndRefreshToken(LoginRequestDto body) {
        Member foundMember = memberRepository.findByUsername(body.getUsername())
            .orElseThrow(NoSuchUsernameException::new);

        if (!passwordEncoder.matches(body.getPassword(), foundMember.getPassword())) {
            throw new PasswordNotMatchException();
        }

        return jwtTokenFactory.generateJwtToken(foundMember);
    }

    /**
     * 리프레시 토큰으로 액세스 토큰 생성
     */
    @Transactional(readOnly = true)
    public AccessTokenResponseDto refreshAccessToken(RefreshTokenRequestDto body) {
        RefreshToken refreshToken = jwtTokenFactory.findRefreshToken(body.getRefreshToken());
        Member member = memberRepository.findById(refreshToken.getMemberId())
            .orElseThrow(MemberNotFoundException::new);

        return jwtTokenFactory.generateAccessToken(member);
    }

    /**
     * 리프레시 토큰 삭제
     */
    public void expirationRefreshToken(RefreshTokenRequestDto body) {
        jwtTokenFactory.expirationRefreshToken(body.getRefreshToken());
    }

}
