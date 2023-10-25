package dbwls.cloudProject.member.controller;

import dbwls.cloudProject.member.dto.MemberDto;
import dbwls.cloudProject.member.dto.MemberPostDto;
import dbwls.cloudProject.member.dto.MyPageDto;
import dbwls.cloudProject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberPostDto memberPostDto) {
        return memberService.createMemberAccountAndSave(memberPostDto);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<MemberDto> getMemberInfo(@PathVariable String nickname) {
        return memberService.getMemberInformation(nickname);
    }

    @PostMapping("/{nickname}")
    public ResponseEntity<Void> checkDuplicatedNickname(@PathVariable String nickname) {
        return memberService.checkNicknameIsDuplicated(nickname);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my-page")
    public ResponseEntity<MyPageDto> getMyPageInfo() {
        return memberService.getMyPageInfo(getLoggedInNickname());
    }

    private String getLoggedInNickname() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
