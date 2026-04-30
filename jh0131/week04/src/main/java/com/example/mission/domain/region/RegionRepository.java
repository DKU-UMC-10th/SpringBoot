package com.example.mission.domain.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for Region entities.  Extending JpaRepository gives us
 * basic CRUD operations out of the box.  Additional query methods can be
 * defined by following Spring Data JPA naming conventions.
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    // Additional query methods may be declared here if necessary
}