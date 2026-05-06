package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 내가 진행중/완료한 미션 목록 (페이징)
    @Query(value = "SELECT um FROM UserMission um " +
                   "JOIN FETCH um.mission m " +
                   "JOIN FETCH m.store s " +
                   "WHERE um.member.id = :memberId " +
                   "AND um.status = :status",
           countQuery = "SELECT COUNT(um) FROM UserMission um " +
                        "WHERE um.member.id = :memberId " +
                        "AND um.status = :status")
    Page<UserMission> findByMemberIdAndStatus(@Param("memberId") Long memberId,
                                              @Param("status") MissionStatus status,
                                              Pageable pageable);
}
