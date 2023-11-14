package com.wanted.budget.guardian.common.config.security.context;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginMember {

    private final Long id;

    private final String username;

    public static LoginMember of(Long id, String username) {
        return new LoginMember(id, username);
    }

}
