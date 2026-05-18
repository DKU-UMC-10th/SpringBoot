package com.example.umc10th.domain.review.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ReviewImage {
    // 리뷰 첨부 이미지 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
