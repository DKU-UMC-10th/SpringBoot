package com.hmooko.week07.domain.review.dto;

import java.time.LocalDate;

public record ReviewCreateResponse(
        Long reviewId,
        Long storeId,
        Long userId,
        Integer stars,
        String content,
        LocalDate createdDate
) {
}
