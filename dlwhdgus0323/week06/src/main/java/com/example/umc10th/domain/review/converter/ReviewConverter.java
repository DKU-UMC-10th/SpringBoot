package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.dto.request.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewResDTO;
import com.example.umc10th.domain.store.entity.Store;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.WriteReview request, Member member, Store store) {
        return Review.builder()
                .body(request.content())
                .score(request.star().floatValue())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.WriteReview toWriteReviewResponse(Review review) {
        return new ReviewResDTO.WriteReview(review.getId());
    }
}
