package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면: 선택된 지역에서 도전 가능한 미션 목록 (내가 이미 도전 중인 미션 제외, 페이징)
    @Query(value = "SELECT m FROM Mission m " +
                   "JOIN FETCH m.store s " +
                   "WHERE s.region.id = :regionId " +
                   "AND m.id NOT IN (" +
                   "    SELECT um.mission.id FROM UserMission um " +
                   "    WHERE um.member.id = :memberId " +
                   "    AND um.status = :status" +
                   ")",
           countQuery = "SELECT COUNT(m) FROM Mission m " +
                        "JOIN m.store s " +
                        "WHERE s.region.id = :regionId " +
                        "AND m.id NOT IN (" +
                        "    SELECT um.mission.id FROM UserMission um " +
                        "    WHERE um.member.id = :memberId " +
                        "    AND um.status = :status" +
                        ")")
    Page<Mission> findAvailableMissionsByRegion(@Param("regionId") Long regionId,
                                                @Param("memberId") Long memberId,
                                                @Param("status") MissionStatus status,
                                                Pageable pageable);
}
