package com.hmooko.week05.domain.review.dto;

import java.time.LocalDate;

public record ReviewCreateResponse(
        Long reviewId,
        Long storeId,
        Integer stars,
        String content,
        Integer imageCount,
        LocalDate createdDate
) {
}
