package com.example.mission.domain.mission;

import com.example.mission.domain.store.Store;
import com.example.mission.domain.store.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for Mission domain.  Handles creation and retrieval of missions.
 * Missions are associated with a store; therefore the StoreRepository is
 * injected to load the parent store during creation.
 */
@Service
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionService(MissionRepository missionRepository, StoreRepository storeRepository) {
        this.missionRepository = missionRepository;
        this.storeRepository = storeRepository;
    }

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Optional<Mission> findById(Long id) {
        return missionRepository.findById(id);
    }

    public List<Mission> findByStore(Long storeId) {
        return missionRepository.findByStoreId(storeId);
    }

    @Transactional
    public Mission createMission(Long storeId, String title, String description, Integer rewardPoints) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + storeId));
        Mission mission = new Mission(store, title, description, rewardPoints);
        return missionRepository.save(mission);
    }
}