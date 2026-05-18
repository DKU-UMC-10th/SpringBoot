package com.hmooko.week06.domain.food.domain;

import com.hmooko.week06.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "food")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Builder
    public Food(String name) {
        this.name = name;
    }
}
