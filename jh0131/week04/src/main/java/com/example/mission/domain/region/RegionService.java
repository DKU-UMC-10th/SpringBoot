package com.example.mission.domain.region;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing regions.  In a domain‑centric architecture the
 * service encapsulates business logic and orchestrates calls to the
 * repository.  Although the current implementation simply delegates to
 * RegionRepository, this layer provides a place to implement rules later
 * without coupling controllers directly to persistence logic.
 */
@Service
@Transactional(readOnly = true)
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Optional<Region> findById(Long id) {
        return regionRepository.findById(id);
    }

    @Transactional
    public Region createRegion(String name) {
        Region region = new Region(name);
        return regionRepository.save(region);
    }
}