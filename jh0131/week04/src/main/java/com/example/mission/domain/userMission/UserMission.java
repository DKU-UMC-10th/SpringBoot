package com.example.mission.domain.userMission;

import com.example.mission.domain.user.User;
import com.example.mission.domain.mission.Mission;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * UserMission links a user with a mission and tracks the completion status.
 * The ERD defines columns user_mission_id, user_id, mission_id, status and
 * completed_at.  A mission can be in various states such as "PENDING" or
 * "COMPLETED"; in a real application this would be modelled as an enum.
 */
@Entity
@Table(name = "user_mission")
public class UserMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    protected UserMission() {
        // for JPA
    }

    public UserMission(User user, Mission mission, String status, LocalDateTime completedAt) {
        this.user = user;
        this.mission = mission;
        this.status = status;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Mission getMission() {
        return mission;
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