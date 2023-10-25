package dbwls.cloudProject.member.service;

import dbwls.cloudProject.common.exception.CustomException;
import dbwls.cloudProject.common.exception.ErrorCode;
import dbwls.cloudProject.member.dto.MemberDto;
import dbwls.cloudProject.member.dto.MemberPostDto;
import dbwls.cloudProject.member.dto.MyPageDto;
import dbwls.cloudProject.member.entity.Member;
import dbwls.cloudProject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Void> createMemberAccountAndSave(MemberPostDto memberPostDto) {
        memberRepository.save(memberPostDto.toEntityWithEncoder(passwordEncoder));
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<MemberDto> getMemberInformation(String nickname) {
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        return ResponseEntity.ok(MemberDto.createWith(member));
    }

    public ResponseEntity<Void> checkNicknameIsDuplicated(String nickname) {
        if (memberRepository.findByNickname(nickname).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<MyPageDto> getMyPageInfo(String nickname) {
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        return ResponseEntity.ok(MyPageDto.createWith(member));
    }
}
