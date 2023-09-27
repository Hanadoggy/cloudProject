package dbwls.cloudProject.member.controller;

import dbwls.cloudProject.member.dto.MemberPostDto;
import dbwls.cloudProject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody MemberPostDto memberPostDto) {
        return memberService.createMemberAccountAndSave(memberPostDto.toEntity());
        // TODO: 포스트맨으로 테스트해보기, 테스트 코드로 테스트하는 방법 구상해보기
    }
}
