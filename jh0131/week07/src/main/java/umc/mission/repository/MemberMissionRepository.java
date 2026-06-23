package umc.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.mission.domain.MemberMission;
import umc.mission.domain.MissionStatus;
import umc.mission.web.dto.MemberMissionResponse;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    long countByMemberIdAndStatus(Long memberId, MissionStatus status);

    @Query("""
            select new umc.mission.web.dto.MemberMissionResponse(
                mm.id,
                m.id,
                s.name,
                m.missionSpec,
                m.reward,
                m.deadline,
                mm.status
            )
            from MemberMission mm
            join mm.mission m
            join m.store s
            where mm.member.id = :memberId
              and mm.status = :status
            """)
    Page<MemberMissionResponse> findMemberMissions(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
