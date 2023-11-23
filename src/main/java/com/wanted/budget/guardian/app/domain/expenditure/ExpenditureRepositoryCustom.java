package com.wanted.budget.guardian.app.domain.expenditure;

import com.wanted.budget.guardian.app.domain.member.Member;
import com.wanted.budget.guardian.app.web.dto.expenditure.SearchExpenditureRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenditureRepositoryCustom {

    Page<Expenditure> findExpenditureListBySearch(SearchExpenditureRequest filter,
        Member member, Pageable pageable);

}
