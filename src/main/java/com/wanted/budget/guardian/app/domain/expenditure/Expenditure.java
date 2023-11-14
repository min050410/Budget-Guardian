package com.wanted.budget.guardian.app.domain.expenditure;

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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Expenditure extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expenditure_id")
    private Long id;

    @Column(nullable = false)
    private Long expense;

    @Lob
    private String memo;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isIncludingExpenditure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Expenditure(Long expense, String memo, Member member, Category category) {
        this.expense = expense;
        this.memo = memo;
        this.member = member;
        this.category = category;
    }

    public boolean isAccessibleToExpenditure(Member member) {
        if (member == null) {
            return false;
        }

        Long loginMemberId = member.getId();
        Long memberId = this.member.getId();
        return memberId.equals(loginMemberId);
    }

}
