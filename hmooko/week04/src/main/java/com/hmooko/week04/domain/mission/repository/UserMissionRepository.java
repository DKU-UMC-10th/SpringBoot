package com.hmooko.week04.domain.mission.repository;

import com.hmooko.week04.domain.mission.domain.UserMission;
import com.hmooko.week04.domain.mission.domain.UserMissionId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, UserMissionId> {

    List<UserMission> findAllByUser_Id(Long userId);
    List<UserMission> findAllByMission_Id(Long missionId);
}
