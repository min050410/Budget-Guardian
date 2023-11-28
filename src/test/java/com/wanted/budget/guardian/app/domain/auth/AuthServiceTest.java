package com.wanted.budget.guardian.app.domain.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.wanted.budget.guardian.common.exception.PasswordNotMatchException;

import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenFactory jwtTokenFactory;

    @InjectMocks
    private MemberService memberService;

    @InjectMocks
    private AuthService authService;

    Member member;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        member = Member.builder()
            .username("min050410")
            .password("password")
            .build();

        Field field = Member.class.getDeclaredField("id");
        field.setAccessible(true); // 필드에 접근 가능하도록 설정
        field.set(member, 1L);
    }

    @DisplayName("유저를 생성한뒤 생성한 유저의 ID를 반환한다.")
    @Test
    void create_user_success() {
        //given
        SignUpRequestDto request = new SignUpRequestDto(
            "min050410",
            "Qawsedrf1!",
            "Qawsedrf1!"
        );

        when(memberRepository.save(any())).thenReturn(member);

        //when
        final MemberIdResponseDto memberId = memberService.create(request);

        //then
        verify(memberRepository).save(any());
        assertThat(memberId.getMemberId()).isEqualTo(1L);
    }

    @DisplayName("요청시 패스위드쌍이 맞지 않게 유저를 생성하면 예외가 발생한다.")
    @Test
    void create_user_failed_password_not_matched() {
        // given
        SignUpRequestDto request = new SignUpRequestDto(
            "min050410",
            "Qawsedrf1!",
            "Qawsedrf1"
        );

        // when
        // then
        assertThatThrownBy(() -> authService.signUp(request))
            .isInstanceOf(PasswordNotMatchException.class);
    }

    @DisplayName("이미 존재하는 유저네임으로 유저를 생성하면 예외를 발생한다.")
    @Test
    void create_user_failed_username_already_exist() {
        // given
        SignUpRequestDto request = new SignUpRequestDto(
            "min050410",
            "Qawsedrf1!",
            "Qawsedrf1"
        );
        when(memberRepository.isPresentUserByUsername(any())).thenReturn(true);

        // when
        // then
        assertThatThrownBy(() -> authService.signUp(request))
            .isInstanceOf(AlreadyExistUsernameException.class);
    }

    @DisplayName("유저가 로그인한다.")
    @Test
    void login_user_success() {
        // given
        LoginRequestDto request = new LoginRequestDto(
            "min050410",
            "Qawsedrf1!"
        );
        TokenResponseDto response = new TokenResponseDto(
            "access",
            "refresh",
            "1",
            "2"
        );
        when(memberRepository.findByUsername(any())).thenReturn(Optional.ofNullable(member));
        when(jwtTokenFactory.generateJwtToken(any())).thenReturn(response);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        // when
        TokenResponseDto token = authService.generateAccessAndRefreshToken(request);

        // then
        assertThat(token.getAccessToken()).isEqualTo("access");
    }

    @DisplayName("패스워드가 일치하지 않게 로그인시 예외가 발생한다.")
    @Test
    void login_user_failed() {
        // given
        LoginRequestDto request = new LoginRequestDto(
            "min050410",
            "password"
        );
        when(memberRepository.findByUsername(any())).thenReturn(Optional.ofNullable(member));

        // when
        assertThatThrownBy(() -> authService.generateAccessAndRefreshToken(request))
            .isInstanceOf(PasswordNotMatchException.class);
    }

    @DisplayName("유저가 리프레시토큰을 통해 엑세스 토큰을 재발급받는다.")
    @Test
    void refresh_access_token_user_success() {
        // given
        RefreshTokenRequestDto request = new RefreshTokenRequestDto("refresh");
        AccessTokenResponseDto response = new AccessTokenResponseDto("access", "1");
        RefreshToken refreshToken = new RefreshToken(1L, "2", 1L);
        when(jwtTokenFactory.findRefreshToken(any())).thenReturn(refreshToken);
        when(memberRepository.findById(any())).thenReturn(Optional.ofNullable(member));
        when(jwtTokenFactory.generateAccessToken(member)).thenReturn(response);

        // when
        AccessTokenResponseDto accessToken = authService.refreshAccessToken(request);

        // then
        assertThat(accessToken.getAccessToken()).isEqualTo("access");
    }

}
