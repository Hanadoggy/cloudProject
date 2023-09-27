package dbwls.cloudProject.member.service;

import dbwls.cloudProject.member.entity.Member;
import dbwls.cloudProject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public ResponseEntity<?> createMemberAccountAndSave(Member member) {
        memberRepository.save(member);
        return ResponseEntity.ok().build();
    }
}
