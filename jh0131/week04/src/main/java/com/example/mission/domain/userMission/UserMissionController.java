package com.example.mission.domain.userMission;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for user missions.  Provides endpoints to list all
 * user missions, retrieve a single record, query by user or mission and
 * create new user mission records.  The creation endpoint accepts a DTO
 * containing userId, missionId, status and completedAt.  The completedAt
 * field is optional and may be omitted for missions that are still in
 * progress.
 */
@RestController
@RequestMapping("/user-missions")
public class UserMissionController {
    private final UserMissionService userMissionService;

    public UserMissionController(UserMissionService userMissionService) {
        this.userMissionService = userMissionService;
    }

    @GetMapping
    public List<UserMission> getUserMissions() {
        return userMissionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMission> getUserMission(@PathVariable Long id) {
        return userMissionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserMission> getUserMissionsByUser(@PathVariable Long userId) {
        return userMissionService.findByUser(userId);
    }

    @GetMapping("/mission/{missionId}")
    public List<UserMission> getUserMissionsByMission(@PathVariable Long missionId) {
        return userMissionService.findByMission(missionId);
    }

    @PostMapping
    public ResponseEntity<UserMission> createUserMission(@RequestBody UserMissionDto dto) {
        LocalDateTime completedAt = dto.getCompletedAt();
        UserMission userMission = userMissionService.createUserMission(
                dto.getUserId(),
                dto.getMissionId(),
                dto.getStatus(),
                completedAt
        );
        return ResponseEntity.ok(userMission);
    }

    /**
     * DTO for creating user mission associations.  Fields correspond to
     * foreign keys and status.  The completedAt property is optional.
     */
    public static class UserMissionDto {
        private Long userId;
        private Long missionId;
        private String status;
        private LocalDateTime completedAt;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getMissionId() {
            return missionId;
        }

        public void setMissionId(Long missionId) {
            this.missionId = missionId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public LocalDateTime getCompletedAt() {
            return completedAt;
        }

        public void setCompletedAt(LocalDateTime completedAt) {
            this.completedAt = completedAt;
        }
    }
}