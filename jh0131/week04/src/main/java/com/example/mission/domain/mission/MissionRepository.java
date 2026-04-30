package com.example.mission.domain.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Mission entities.  Provides a derived query to find
 * missions by store ID.
 */
@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByStoreId(Long storeId);
}