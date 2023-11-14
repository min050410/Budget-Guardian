package com.wanted.budget.guardian.app.domain.member;

import com.wanted.budget.guardian.app.domain.BaseCreateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    private Long amount;

    @Builder
    private Member(String username, String password, Long amount) {
        this.username = username;
        this.password = password;
        this.amount = amount;
    }

}
