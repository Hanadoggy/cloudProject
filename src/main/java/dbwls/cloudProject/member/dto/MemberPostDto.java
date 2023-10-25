package dbwls.cloudProject.member.dto;

import dbwls.cloudProject.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
public class MemberPostDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String phone;
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
