package com.hmooko.week07.domain.review.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyReviewCursorRequest {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long userId;

    private Long cursorId;

    @Min(value = 1, message = "size는 1 이상이어야 합니다.")
    private int size = 10;
}
