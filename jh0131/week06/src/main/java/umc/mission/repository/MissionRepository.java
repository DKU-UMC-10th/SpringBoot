package umc.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.mission.domain.Mission;
import umc.mission.web.dto.HomeMissionResponse;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            select new umc.mission.web.dto.HomeMissionResponse(
                m.id,
                s.id,
                s.name,
                m.missionSpec,
                m.reward,
                m.deadline
            )
            from Mission m
            join m.store s
            join s.region r
            where r.id = :regionId
              and m.deadline >= current_date
              and not exists (
                  select 1
                  from MemberMission mm
                  where mm.member.id = :memberId
                    and mm.mission = m
              )
            order by m.deadline asc, m.id desc
            """)
    Page<HomeMissionResponse> findAvailableMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}

