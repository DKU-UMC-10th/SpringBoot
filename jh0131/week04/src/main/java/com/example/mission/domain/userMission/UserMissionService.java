package com.example.mission.domain.userMission;

import com.example.mission.domain.user.User;
import com.example.mission.domain.user.UserRepository;
import com.example.mission.domain.mission.Mission;
import com.example.mission.domain.mission.MissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service for user‑mission relationships.  Handles creation and retrieval of
 * user missions.  Associates the user and mission by loading them from
 * their respective repositories.
 */
@Service
@Transactional(readOnly = true)
public class UserMissionService {
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    public UserMissionService(UserMissionRepository userMissionRepository, UserRepository userRepository, MissionRepository missionRepository) {
        this.userMissionRepository = userMissionRepository;
        this.userRepository = userRepository;
        this.missionRepository = missionRepository;
    }

    public List<UserMission> findAll() {
        return userMissionRepository.findAll();
    }

    public Optional<UserMission> findById(Long id) {
        return userMissionRepository.findById(id);
    }

    public List<UserMission> findByUser(Long userId) {
        return userMissionRepository.findByUserId(userId);
    }

    public List<UserMission> findByMission(Long missionId) {
        return userMissionRepository.findByMissionId(missionId);
    }

    @Transactional
    public UserMission createUserMission(Long userId, Long missionId, String status, LocalDateTime completedAt) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found: " + missionId));
        UserMission userMission = new UserMission(user, mission, status, completedAt);
        return userMissionRepository.save(userMission);
    }
}