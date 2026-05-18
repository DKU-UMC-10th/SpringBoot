package com.hmooko.week07.domain.mission.domain;

import com.hmooko.week07.domain.store.domain.Store;
import com.hmooko.week07.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Column(nullable = false)
    private String missionSpec;

    @Column(nullable = false)
    private Integer accomplishedAmount;

    @Column(nullable = false)
    private Integer accumulationPoint;

    @Column(nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder
    public Mission(
            String missionSpec,
            Integer accomplishedAmount,
            Integer accumulationPoint,
            LocalDate deadline,
            Store store
    ) {
        this.missionSpec = missionSpec;
        this.accomplishedAmount = accomplishedAmount;
        this.accumulationPoint = accumulationPoint;
        this.deadline = deadline;
        this.store = store;
    }
}
