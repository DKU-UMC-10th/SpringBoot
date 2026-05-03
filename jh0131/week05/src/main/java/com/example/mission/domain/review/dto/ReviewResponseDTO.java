package com.example.mission.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    public record CreateReviewResponse(
            Long reviewId,
            Long storeId,
            Long userId,
            Integer score,
            String content,
            LocalDateTime createdAt
    ) {
    }
}
