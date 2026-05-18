package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.review.dto.request.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.domain.store.exception.StoreSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.WriteReview> writeReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.WriteReview dto
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.WRITE_REVIEW, reviewService.writeReview(storeId, dto));
    }
}
