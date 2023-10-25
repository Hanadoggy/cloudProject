package dbwls.cloudProject.member.dto;

import dbwls.cloudProject.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class MemberPostDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String phone;
    @NotBlank
    @Size(min = 8, max = 32)
    private String password;

    public Member toEntityWithEncoder(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nickname(nickname)
                .phoneNumber(phone)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
