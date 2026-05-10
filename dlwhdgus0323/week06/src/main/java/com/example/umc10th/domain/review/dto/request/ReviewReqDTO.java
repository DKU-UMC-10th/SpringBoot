package com.example.umc10th.domain.review.dto.request;

public class ReviewReqDTO {

    public record WriteReview(
            Long storeId,
            String content,
            Integer star
    ) {}
}
