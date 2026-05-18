package com.hmooko.week05.domain.mission.repository;

import com.hmooko.week05.domain.mission.domain.Mission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findAllByStore_Id(Long storeId);
}
