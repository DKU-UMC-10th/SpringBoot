package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.dto.request.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewResDTO;
import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

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

    public static ReviewResDTO.ReviewPreview toReviewPreview(Review review) {
        return new ReviewResDTO.ReviewPreview(
                review.getId(),
                review.getStore().getName(),
                review.getBody(),
                review.getScore()
        );
    }

    public static ReviewResDTO.ReviewCursorResult toReviewCursorResult(
            Slice<Review> slice, String sort) {
        List<ReviewResDTO.ReviewPreview> reviewList = slice.getContent().stream()
                .map(ReviewConverter::toReviewPreview)
                .collect(Collectors.toList());

        String nextCursor = null;
        if (slice.hasNext() && !reviewList.isEmpty()) {
            Review last = slice.getContent().get(slice.getContent().size() - 1);
            if ("STAR".equalsIgnoreCase(sort)) {
                nextCursor = "STAR:" + last.getScore() + ":" + last.getId();
            } else {
                nextCursor = "ID:" + last.getId();
            }
        }

        return new ReviewResDTO.ReviewCursorResult(
                reviewList,
                reviewList.size(),
                slice.hasNext(),
                nextCursor
        );
    }
}
