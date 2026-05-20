package com.hmooko.week06.domain.review.controller;

import com.hmooko.week06.domain.review.dto.ReviewCreateRequest;
import com.hmooko.week06.domain.review.dto.ReviewCreateResponse;
import com.hmooko.week06.domain.review.service.ReviewService;
import com.hmooko.week06.domain.review.dto.StoreReviewResponse;
import com.hmooko.week06.global.apiPayload.PageResponse;
import com.hmooko.week06.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewCreateResponse> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewCreateRequest request
    ) {
        return ApiResponse.onSuccess(reviewService.createReview(storeId, request));
    }

    @GetMapping
    public ApiResponse<PageResponse<StoreReviewResponse>> getReviews(
            @PathVariable Long storeId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ApiResponse.onSuccess(reviewService.getReviews(storeId, pageable));
    }
}
