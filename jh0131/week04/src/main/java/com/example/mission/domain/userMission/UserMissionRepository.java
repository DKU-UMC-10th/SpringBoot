package com.example.mission.domain.userMission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for UserMission entities.  Provides queries to find missions
 * completed by a user or by mission.
 */
@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    List<UserMission> findByUserId(Long userId);
    List<UserMission> findByMissionId(Long missionId);
}