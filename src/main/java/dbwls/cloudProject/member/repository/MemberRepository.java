package dbwls.cloudProject.member.repository;

import dbwls.cloudProject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
