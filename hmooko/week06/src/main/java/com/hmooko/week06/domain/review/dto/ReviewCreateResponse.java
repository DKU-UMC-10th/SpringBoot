package com.hmooko.week06.domain.review.dto;

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
