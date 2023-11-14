package com.wanted.budget.guardian.app.web.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberIdResponseDto {

    private Long memberId;

    public static MemberIdResponseDto of(Long memberId) {
        return new MemberIdResponseDto(memberId);
    }

}