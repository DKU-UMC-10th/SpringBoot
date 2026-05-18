package com.hmooko.week07.domain.review.dto;

import java.util.List;

public record MyReviewCursorResponse(
        List<MyReviewCursorItem> content,
        Long nextCursor,
        int size,
        boolean hasNext
) {
}
