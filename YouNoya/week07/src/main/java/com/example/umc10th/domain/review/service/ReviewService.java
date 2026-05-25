package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.Review;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResponseDTO.CreateReviewResultDTO createReview(Long storeId,
                                                                ReviewRequestDTO.CreateReviewDTO request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Review review = Review.builder()
                .content(request.content())
                .rating(request.rating())
                .member(member)
                .store(store)
                .build();

        Review saved = reviewRepository.save(review);

        return ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(saved.getId())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    // 내가 생성한 리뷰 목록 - 커서 기반 페이지네이션 (Task 2)
    // sort: "ID" → id 순 (기본), "RATING" → 별점 순
    // cursor 형식: ID 순 → "ID:733", 별점 순 → "RATING:4.0:733"
    @Transactional(readOnly = true)
    public ReviewResponseDTO.ReviewCursorListDTO getMyReviews(
            Long memberId, String cursor, Integer size, String sort) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, size);
        Slice<Review> reviews;

        if ("RATING".equalsIgnoreCase(sort)) {
            if (cursor == null) {
                reviews = reviewRepository.findByMemberIdOrderByRatingDescIdDesc(memberId, pageable);
            } else {
                // cursor = "RATING:4.0:733"
                String[] parts = cursor.split(":");
                Float cursorRating = Float.parseFloat(parts[1]);
                Long cursorId = Long.parseLong(parts[2]);
                reviews = reviewRepository.findByMemberIdAfterRatingCursor(memberId, cursorRating, cursorId, pageable);
            }
        } else {
            // ID 순 (기본)
            if (cursor == null) {
                reviews = reviewRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
            } else {
                // cursor = "ID:733"
                Long cursorId = Long.parseLong(cursor.split(":")[1]);
                reviews = reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, cursorId, pageable);
            }
        }

        List<Review> content = reviews.getContent();

        List<ReviewResponseDTO.ReviewPreviewDTO> dtoList = content.stream()
                .map(r -> ReviewResponseDTO.ReviewPreviewDTO.builder()
                        .reviewId(r.getId())
                        .storeName(r.getStore().getName())
                        .rating(r.getRating())
                        .content(r.getContent())
                        .createdAt(r.getCreatedAt())
                        .build())
                .toList();

        String nextCursor = null;
        if (reviews.hasNext() && !content.isEmpty()) {
            Review last = content.get(content.size() - 1);
            nextCursor = "RATING".equalsIgnoreCase(sort)
                    ? "RATING:" + last.getRating() + ":" + last.getId()
                    : "ID:" + last.getId();
        }

        return ReviewResponseDTO.ReviewCursorListDTO.builder()
                .reviewList(dtoList)
                .listSize(dtoList.size())
                .hasNext(reviews.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
