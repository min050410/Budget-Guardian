package com.wanted.budget.guardian.app.domain.budget;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long>, BudgetRepositoryCustom { }