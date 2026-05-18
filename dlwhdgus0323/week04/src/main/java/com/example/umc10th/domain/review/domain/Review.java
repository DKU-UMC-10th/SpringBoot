package com.example.umc10th.domain.review.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Review {
    // 리뷰 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
