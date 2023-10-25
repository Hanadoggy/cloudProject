package dbwls.cloudProject.member.dto;

import dbwls.cloudProject.member.entity.Member;

public record MyPageDto(String nickname, String phone) {
    public static MyPageDto createWith(Member member) {
        return new MyPageDto(member.getNickname(), member.getPhoneNumber());
    }
}
