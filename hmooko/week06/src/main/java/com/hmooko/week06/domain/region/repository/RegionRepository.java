package com.hmooko.week06.domain.region.repository;

import com.hmooko.week06.domain.region.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
