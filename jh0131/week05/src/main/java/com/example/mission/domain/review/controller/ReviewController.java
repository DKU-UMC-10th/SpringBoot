package com.example.mission.domain.review.controller;

import com.example.mission.domain.review.code.ReviewSuccessCode;
import com.example.mission.domain.review.dto.ReviewRequestDTO;
import com.example.mission.domain.review.dto.ReviewResponseDTO;
import com.example.mission.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
public class ReviewController {

    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResponseDTO.CreateReviewResponse>> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewRequestDTO.CreateReviewRequest request
    ) {
        ReviewResponseDTO.CreateReviewResponse response = new ReviewResponseDTO.CreateReviewResponse(
                1L,
                storeId,
                request.userId(),
                request.score(),
                request.content(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(ReviewSuccessCode.CREATE_REVIEW.getStatus())
                .body(ApiResponse.onSuccess(ReviewSuccessCode.CREATE_REVIEW, response));
    }
}
