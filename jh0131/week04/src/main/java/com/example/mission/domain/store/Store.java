package com.example.mission.domain.store;

import com.example.mission.domain.region.Region;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Store represents a physical shop or location that participates in missions.
 * Each store belongs to a region and has attributes such as name, address,
 * description, category and a score.  The creation timestamp is recorded
 * automatically when the entity is persisted.  This entity reflects the
 * columns described in the user’s ERD: store_id, region_id, name, address,
 * description, category, score and created_at.
 */
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(length = 200)
    private String description;

    @Column(length = 20)
    private String category;

    private Float score;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Store() {
        // for JPA
    }

    public Store(Region region, String name, String address, String description, String category, Float score) {
        this.region = region;
        this.name = name;
        this.address = address;
        this.description = description;
        this.category = category;
        this.score = score;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}