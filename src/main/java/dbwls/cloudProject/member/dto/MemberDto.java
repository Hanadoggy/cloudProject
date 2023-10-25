package dbwls.cloudProject.member.dto;

import dbwls.cloudProject.member.entity.Member;

public record MemberDto(String nickname) {
    public static MemberDto createWith(Member member) {
        return new MemberDto(member.getNickname());
    }
}
