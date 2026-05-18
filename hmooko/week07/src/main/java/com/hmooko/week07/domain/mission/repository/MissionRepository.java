package com.hmooko.week07.domain.mission.repository;

import com.hmooko.week07.domain.mission.domain.Mission;
import com.hmooko.week07.domain.mission.dto.HomeMissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            select new com.hmooko.week07.domain.mission.dto.HomeMissionResponse(
                m.id,
                s.id,
                s.name,
                m.missionSpec,
                m.accumulationPoint,
                m.deadline
            )
            from Mission m
            join m.store s
            join s.region r
            where r.id = :regionId
              and m.deadline >= current_date
              and not exists (
                  select 1
                  from UserMission um
                  where um.user.id = :userId
                    and um.mission = m
              )
            order by m.deadline asc, m.id desc
            """)
    Page<HomeMissionResponse> findHomeMissionPage(
            @Param("userId") Long userId,
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}
