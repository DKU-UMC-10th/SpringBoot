package com.example.umc10th.domain.store.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Store {
    // 가게 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
