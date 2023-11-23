package com.wanted.budget.guardian.app.domain.expenditure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long>,
    ExpenditureRepositoryCustom {
}
