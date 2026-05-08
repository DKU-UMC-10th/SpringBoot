package umc.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mission.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

