package com.wanted.budget.guardian.app.domain.member;

import com.wanted.budget.guardian.app.web.dto.auth.SignUpRequestDto;
import com.wanted.budget.guardian.app.web.dto.member.MemberIdResponseDto;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 멤버 생성
     */
    @Transactional
    public MemberIdResponseDto create(SignUpRequestDto body) {
        String encodedPassword = encodePassword(body.getPassword());
        Member member = body.toMember(encodedPassword);

        memberRepository.save(member);
        return MemberIdResponseDto.of(member.getId());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Transactional(readOnly = true)
    public Member findLoginMember(LoginMember loginMember) {
        if (loginMember == null) {
            return null;
        }

        Long loginMemberId = loginMember.getId();
        return memberRepository.getReferenceById(loginMemberId);
    }

}
