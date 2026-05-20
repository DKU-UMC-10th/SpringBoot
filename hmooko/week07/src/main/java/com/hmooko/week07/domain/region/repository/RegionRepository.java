package com.hmooko.week07.domain.region.repository;

import com.hmooko.week07.domain.region.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
