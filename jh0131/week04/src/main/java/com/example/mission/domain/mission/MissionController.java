package com.example.mission.domain.mission;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing endpoints for missions.  Supports listing
 * missions, retrieving a specific mission, listing missions by store and
 * creating new missions.  Incoming JSON bodies are mapped to a DTO to
 * decouple the API contract from the domain model.
 */
@RestController
@RequestMapping("/missions")
public class MissionController {
    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public List<Mission> getMissions() {
        return missionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMission(@PathVariable Long id) {
        return missionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/store/{storeId}")
    public List<Mission> getMissionsByStore(@PathVariable Long storeId) {
        return missionService.findByStore(storeId);
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody MissionDto dto) {
        Mission mission = missionService.createMission(
                dto.getStoreId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getRewardPoints()
        );
        return ResponseEntity.ok(mission);
    }

    /**
     * DTO used when creating a mission.  Contains only the fields required
     * from the client.  More complex validation and conversion logic can
     * be placed in a separate converter class as described in the workbook.
     */
    public static class MissionDto {
        private Long storeId;
        private String title;
        private String description;
        private Integer rewardPoints;

        public Long getStoreId() {
            return storeId;
        }

        public void setStoreId(Long storeId) {
            this.storeId = storeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getRewardPoints() {
            return rewardPoints;
        }

        public void setRewardPoints(Integer rewardPoints) {
            this.rewardPoints = rewardPoints;
        }
    }
}