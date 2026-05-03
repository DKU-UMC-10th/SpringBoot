package com.example.umc10th.domain.mission.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Mission {
    // 미션 엔티티 (핵심 모델)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
