package com.wanted.budget.guardian.app.domain.budget;

import com.wanted.budget.guardian.app.domain.BaseCreateTimeEntity;
import com.wanted.budget.guardian.app.domain.category.Category;
import com.wanted.budget.guardian.app.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Budget extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long id;

    @Column(nullable = false)
    private Long budgetAmount;

    @Column(nullable = false)
    private Long perOfBudgetAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Budget(Long budgetAmount, Long perOfBudgetAmount, Member member,
        Category category) {
        this.budgetAmount = budgetAmount;
        this.perOfBudgetAmount = perOfBudgetAmount;
        this.member = member;
        this.category = category;
    }

    public void updateBudgetAmount(Long budgetAmount, Long perOfBudgetAmount) {
        this.budgetAmount = budgetAmount;
        this.perOfBudgetAmount = perOfBudgetAmount;
    }

}
