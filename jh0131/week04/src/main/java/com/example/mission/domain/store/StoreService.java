package com.example.mission.domain.store;

import com.example.mission.domain.region.Region;
import com.example.mission.domain.region.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for stores.  This class orchestrates creation of stores,
 * fetching stores by region and retrieving individual stores.  The service
 * references RegionRepository to load the parent region before persisting a
 * new store.  Transactions ensure that persistence operations occur
 * atomically.
 */
@Service
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    public StoreService(StoreRepository storeRepository, RegionRepository regionRepository) {
        this.storeRepository = storeRepository;
        this.regionRepository = regionRepository;
    }

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Optional<Store> findById(Long id) {
        return storeRepository.findById(id);
    }

    public List<Store> findByRegion(Long regionId) {
        return storeRepository.findByRegionId(regionId);
    }

    @Transactional
    public Store createStore(Long regionId, String name, String address, String description, String category, Float score) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("Region not found: " + regionId));
        Store store = new Store(region, name, address, description, category, score);
        return storeRepository.save(store);
    }
}