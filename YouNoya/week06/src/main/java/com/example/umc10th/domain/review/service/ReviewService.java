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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
