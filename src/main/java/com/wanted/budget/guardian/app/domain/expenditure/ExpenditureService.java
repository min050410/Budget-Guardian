package com.wanted.budget.guardian.app.domain.expenditure;

import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.category.CategoryService;
import com.wanted.budget.guardian.app.domain.member.Member;
import com.wanted.budget.guardian.app.domain.member.MemberService;
import com.wanted.budget.guardian.app.web.dto.expenditure.CreateExpenditureRequestDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureIdResponseDto;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;

    private final CategoryService categoryService;

    private final MemberService memberService;

    /**
     * 지출 기록 생성
     */
    public ExpenditureIdResponseDto create(
        LoginMember loginMember, CreateExpenditureRequestDto body) {
        Member member = memberService.findLoginMember(loginMember);
        Category category = categoryService.findById(body.getCategoryId());

        Expenditure expenditure = body.toExpenditure(member, category);
        expenditureRepository.save(expenditure);
        return ExpenditureIdResponseDto.of(expenditure.getId());
    }


}
