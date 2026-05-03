package com.hmooko.week05.domain.review.controller;

import com.hmooko.week05.domain.review.dto.ReviewCreateRequest;
import com.hmooko.week05.domain.review.dto.ReviewCreateResponse;
import com.hmooko.week05.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewCreateResponse> createReview(
            @PathVariable Long storeId,
            @Valid @ModelAttribute ReviewCreateRequest request
    ) {
        ReviewCreateResponse response = new ReviewCreateResponse(
                1L,
                storeId,
                request.getStars(),
                request.getContent(),
                request.getImages() == null ? 0 : request.getImages().size(),
                LocalDate.now()
        );

        return ApiResponse.onSuccess(response);
    }
}
