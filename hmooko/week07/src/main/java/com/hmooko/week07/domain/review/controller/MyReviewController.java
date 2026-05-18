package com.hmooko.week07.domain.review.controller;

import com.hmooko.week07.domain.review.dto.MyReviewCursorRequest;
import com.hmooko.week07.domain.review.dto.MyReviewCursorResponse;
import com.hmooko.week07.domain.review.service.ReviewService;
import com.hmooko.week07.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class MyReviewController {

    private final ReviewService reviewService;

    @PostMapping("/my")
    public ApiResponse<MyReviewCursorResponse> getMyReviews(@Valid @RequestBody MyReviewCursorRequest request) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(request));
    }
}
