package com.hmooko.week06.domain.mission.repository;

import com.hmooko.week06.domain.mission.domain.MissionStatus;
import com.hmooko.week06.domain.mission.domain.UserMission;
import com.hmooko.week06.domain.mission.dto.UserMissionPreviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    long countByUser_IdAndStatus(Long userId, MissionStatus status);

    @Query("""
            select new com.hmooko.week06.domain.mission.dto.UserMissionPreviewResponse(
                um.id,
                m.id,
                s.name,
                m.missionSpec,
                m.accumulationPoint,
                m.deadline,
                um.status
            )
            from UserMission um
            join um.mission m
            join m.store s
            where um.user.id = :userId
              and um.status = :status
            order by um.createdAt desc
            """)
    Page<UserMissionPreviewResponse> findMissionPageByUserIdAndStatus(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
