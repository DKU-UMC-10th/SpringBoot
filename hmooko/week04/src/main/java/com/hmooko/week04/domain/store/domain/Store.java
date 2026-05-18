package com.hmooko.week04.domain.store.domain;

import com.hmooko.week04.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private String category;

    @Builder
    public Store(String name, String address, LocalTime startTime, LocalTime endTime, String category) {
        this.name = name;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }
}
