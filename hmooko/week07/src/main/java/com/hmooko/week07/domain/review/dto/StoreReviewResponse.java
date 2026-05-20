package com.hmooko.week07.domain.review.dto;

import java.time.LocalDate;

public record StoreReviewResponse(
        Long reviewId,
        Long userId,
        String userNickname,
        Integer point,
        String content,
        LocalDate createdDate
) {
}
