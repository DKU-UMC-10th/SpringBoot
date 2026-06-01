package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreRestController {

    private final ReviewService reviewService;

    // 리뷰 작성 (사진 배제)
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request
    ) {
        return ApiResponse.onSuccess(reviewService.createReview(storeId, request));
    }
}
