package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.request.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewResDTO;
import com.example.umc10th.domain.review.exception.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{userId}/reviews")
    public ApiResponse<ReviewResDTO.WriteReview> writeReview(
            @PathVariable Long userId,
            @RequestBody ReviewReqDTO.WriteReview request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.WRITE_REVIEW, reviewService.writeReview(userId, request));
    }
}
