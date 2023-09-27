package dbwls.cloudProject.member.dto;

import dbwls.cloudProject.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class MemberPostDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String phone;
    // TODO: 휴대폰 번호 양식 검증 필요
    @NotBlank
    @Range(min = 8)
    private String password;
    // TODO: 비밀번호 평문 저장이 아닌 해시 코드로 변환하여 저장 + 개인 salt값 부여 후 조합 변환

    public Member toEntity() {
        return Member.builder()
                .name(nickname)
                .phoneNumber(phone)
                .password(password)
                .build();
    }
}
