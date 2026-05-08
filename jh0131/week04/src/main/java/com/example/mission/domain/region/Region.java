package com.example.mission.domain.region;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Region represents a geographical area that groups stores.  Each region
 * contains a unique identifier and a human‑readable name.  This entity is
 * modelled directly from the ERD provided by the user, which defines the
 * table with columns region_id (primary key) and name.  JPA annotations are
 * used here to map the class to the underlying database table.
 */
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    // Default constructor required by JPA
    protected Region() {}

    public Region(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}