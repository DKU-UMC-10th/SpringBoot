package com.example.mission.domain.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for persisting Store entities.  Provides a derived query method
 * to find stores by their region, returning all stores associated with a
 * particular region.  Additional queries may be added following Spring Data
 * naming conventions.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByRegionId(Long regionId);
}