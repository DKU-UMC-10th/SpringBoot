package com.example.umc10th.domain.review.dto.response;

import java.util.List;

public class ReviewResDTO {

    public record WriteReview(Long reviewId) {}

    public record ReviewPreview(
            Long reviewId,
            String storeName,
            String body,
            Float score
    ) {}

    public record ReviewCursorResult(
            List<ReviewPreview> reviewList,
            Integer listSize,
            Boolean hasNext,
            String nextCursor
    ) {}
}
