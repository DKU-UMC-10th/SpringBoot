package com.example.mission.domain.mission;

import com.example.mission.domain.store.Store;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Mission represents a task that users can complete at a store to earn reward
 * points.  The mission is associated with a store and contains a title,
 * description, reward points and the date/time it was created.  This entity
 * corresponds directly to the mission table defined in the ERD.
 */
@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200)
    private String description;

    @Column(name = "reward_points", nullable = false)
    private Integer rewardPoints;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Mission() {
        // for JPA
    }

    public Mission(Store store, String title, String description, Integer rewardPoints) {
        this.store = store;
        this.title = title;
        this.description = description;
        this.rewardPoints = rewardPoints;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}