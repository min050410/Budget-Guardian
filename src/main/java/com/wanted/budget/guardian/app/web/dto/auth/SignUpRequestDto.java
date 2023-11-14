package com.wanted.budget.guardian.app.web.dto.auth;

import com.wanted.budget.guardian.app.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SignUpRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요")
    @Schema(description = "닉네임")
    @Size(max = 40, message = "닉네임은 40자 이내로 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
        message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    @Schema(description = "패스워드")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    @Schema(description = "비밀번호 확인")
    private String checkedPassword;

    public Member toMember(String encodedPassword) {
        return Member.builder()
            .username(username)
            .password(encodedPassword)
            .build();
    }

}
