package com.wanted.budget.guardian.app.domain.member;

import com.wanted.budget.guardian.common.exception.MemberNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(final String username);

    Optional<Member> findByUsername(final String username);

    default boolean isPresentUserByUsername(final String username) {
        return findByUsername(username).isPresent();
    }

    default Member getByUsername(final String username) {
        return findByUsername(username)
            .orElseThrow(MemberNotFoundException::new);
    }

}
