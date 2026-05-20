package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.request.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewResDTO;
import com.example.umc10th.domain.review.exception.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{userId}/reviews")
    public ApiResponse<ReviewResDTO.WriteReview> writeReview(
            @PathVariable @NotNull(message = "사용자 ID는 필수입니다.") Long userId,
            @RequestBody @Valid ReviewReqDTO.WriteReview request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.WRITE_REVIEW, reviewService.writeReview(userId, request));
    }

    /**
     * 내가 생성한 리뷰 목록 조회 (커서 기반 페이지네이션)
     * sort: ID (ID순, 기본값) | STAR (별점순)
     * cursor: null=첫페이지, ID:{id}, STAR:{score}:{id}
     */
    @GetMapping("/{userId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewCursorResult> getMyReviews(
            @PathVariable @NotNull(message = "사용자 ID는 필수입니다.") Long userId,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "ID") @NotNull(message = "정렬 기준은 필수입니다.") String sort,
            @RequestParam(defaultValue = "10") @NotNull(message = "페이지 크기는 필수입니다.") @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.") Integer size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.GET_MY_REVIEWS,
                reviewService.getMyReviews(userId, cursor, sort, size));
    }
}
