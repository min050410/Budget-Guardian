package com.wanted.budget.guardian.app.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요")
    @Schema(description = "닉네임")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Schema(description = "패스워드")
    private String password;

}
