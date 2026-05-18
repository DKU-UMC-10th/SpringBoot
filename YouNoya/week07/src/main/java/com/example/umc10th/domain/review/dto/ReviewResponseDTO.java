package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }

    // 리뷰 단건 미리보기 (Task 2)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewDTO {
        Long reviewId;
        String storeName;
        Float rating;
        String content;
        LocalDateTime createdAt;
    }

    // 커서 기반 리뷰 목록 (Task 2)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCursorListDTO {
        List<ReviewPreviewDTO> reviewList;
        Integer listSize;
        Boolean hasNext;
        String nextCursor;
    }
}
