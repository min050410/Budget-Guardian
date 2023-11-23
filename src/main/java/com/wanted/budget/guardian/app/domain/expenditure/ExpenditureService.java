package com.wanted.budget.guardian.app.domain.expenditure;

import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.category.CategoryService;
import com.wanted.budget.guardian.app.domain.member.Member;
import com.wanted.budget.guardian.app.domain.member.MemberService;
import com.wanted.budget.guardian.app.web.dto.expenditure.CreateExpenditureRequestDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureIdResponseDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureDetailResponseDto;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import com.wanted.budget.guardian.common.exception.ExpenditureNotFoundException;
import com.wanted.budget.guardian.common.exception.NotPossibleToAccessExpenditureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * 지출 상세 조회
     */
    public ExpenditureDetailResponseDto findExpenditureDetail(LoginMember loginMember, Long expenditureId) {
        Member member = memberService.findLoginMember(loginMember);
        Expenditure expenditure = findById(expenditureId);

        if (!expenditure.isAccessibleToExpenditure(member)) {
            throw new NotPossibleToAccessExpenditureException();
        }

        return ExpenditureDetailResponseDto.of(expenditure);
    }

    @Transactional(readOnly = true)
    public Expenditure findById(Long expenditureId) {
        return expenditureRepository.findById(expenditureId)
            .orElseThrow(ExpenditureNotFoundException::new);
    }

}
