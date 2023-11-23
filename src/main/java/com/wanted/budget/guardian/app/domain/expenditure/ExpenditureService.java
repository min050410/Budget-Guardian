package com.wanted.budget.guardian.app.domain.expenditure;

import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.category.CategoryService;
import com.wanted.budget.guardian.app.domain.member.Member;
import com.wanted.budget.guardian.app.domain.member.MemberService;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureDetailResponseDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureIdResponseDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditurePagedResponse;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureRequestDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.ExpenditureResponseDto;
import com.wanted.budget.guardian.app.web.dto.expenditure.SearchExpenditureRequest;
import com.wanted.budget.guardian.common.config.response.Pagination;
import com.wanted.budget.guardian.common.config.security.context.LoginMember;
import com.wanted.budget.guardian.common.exception.ExpenditureNotFoundException;
import com.wanted.budget.guardian.common.exception.NotPossibleToAccessExpenditureException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Transactional
    public ExpenditureIdResponseDto create(
        LoginMember loginMember, ExpenditureRequestDto body) {
        Member member = memberService.findLoginMember(loginMember);
        Category category = categoryService.findById(body.getCategoryId());

        Expenditure expenditure = body.toExpenditure(member, category);
        expenditureRepository.save(expenditure);
        return ExpenditureIdResponseDto.of(expenditure.getId());
    }


    /**
     * 지출 상세 조회
     */
    public ExpenditureDetailResponseDto findExpenditureDetail(LoginMember loginMember,
        Long expenditureId) {
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

    /**
     * 지출 목록 조회
     */
    @Transactional(readOnly = true)
    public ExpenditurePagedResponse findExpenditureBySearch(LoginMember loginMember,
        SearchExpenditureRequest body) {
        Member member = memberService.findLoginMember(loginMember);

        Pagination pagination = Pagination.create(body.getPage(), body.getSize());
        PageRequest pageRequest = pagination.toPageRequest();

        Page<Expenditure> expenditureListBySearch = expenditureRepository.findExpenditureListBySearch(
            body, member, pageRequest);

        return ExpenditureResponseDto.pagedListOf(pagination, expenditureListBySearch);
    }

    /**
     * 지출 삭제
     */
    @Transactional
    public void updateExpenditure(LoginMember loginMember, ExpenditureRequestDto body,
        Long expenditureId) {
        Member member = memberService.findLoginMember(loginMember);
        Expenditure expenditure = findById(expenditureId);
        Category category = categoryService.findById(body.getCategoryId());

        if (!expenditure.isAccessibleToExpenditure(member)) {
            throw new NotPossibleToAccessExpenditureException();
        }

        Expenditure requestExpenditure = body.toExpenditure(member, category);
        expenditure.update(requestExpenditure);
    }

    /**
     * 지출 합계 포함
     */
    @Transactional
    public void toggleExpenditureAllowsSumCalculation(LoginMember loginMember, Long expenditureId) {
        Member member = memberService.findLoginMember(loginMember);
        Expenditure expenditure = findById(expenditureId);

        if (!expenditure.isAccessibleToExpenditure(member)) {
            throw new NotPossibleToAccessExpenditureException();
        }

        expenditure.toggleAllowsSumCalculation();
    }

    /**
     * 지출 기록 삭제
     */
    @Transactional
    public void expenditureDelete(LoginMember loginMember, Long expenditureId) {
        Member member = memberService.findLoginMember(loginMember);
        Expenditure expenditure = findById(expenditureId);

        if (!expenditure.isAccessibleToExpenditure(member)) {
            throw new NotPossibleToAccessExpenditureException();
        }

        expenditureRepository.delete(expenditure);
    }

}
