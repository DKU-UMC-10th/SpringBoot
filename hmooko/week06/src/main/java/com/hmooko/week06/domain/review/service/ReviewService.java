package com.hmooko.week06.domain.review.service;

import com.hmooko.week06.domain.review.domain.Review;
import com.hmooko.week06.domain.review.dto.ReviewCreateRequest;
import com.hmooko.week06.domain.review.dto.ReviewCreateResponse;
import com.hmooko.week06.domain.review.dto.StoreReviewResponse;
import com.hmooko.week06.domain.review.repository.ReviewRepository;
import com.hmooko.week06.domain.store.domain.Store;
import com.hmooko.week06.domain.store.repository.StoreRepository;
import com.hmooko.week06.domain.user.domain.User;
import com.hmooko.week06.domain.user.repository.UserRepository;
import com.hmooko.week06.global.apiPayload.PageResponse;
import com.hmooko.week06.global.apiPayload.code.status.ErrorStatus;
import com.hmooko.week06.global.apiPayload.exception.GeneralException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewCreateResponse createReview(Long storeId, ReviewCreateRequest request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND));

        Review savedReview = reviewRepository.save(Review.builder()
                .store(store)
                .user(user)
                .point(request.getStars())
                .content(request.getContent())
                .date(LocalDate.now())
                .build());

        return new ReviewCreateResponse(
                savedReview.getId(),
                storeId,
                user.getId(),
                savedReview.getPoint(),
                savedReview.getContent(),
                savedReview.getDate()
        );
    }

    public PageResponse<StoreReviewResponse> getReviews(Long storeId, Pageable pageable) {
        if (!storeRepository.existsById(storeId)) {
            throw new GeneralException(ErrorStatus.NOT_FOUND);
        }

        return PageResponse.from(reviewRepository.findReviewPageByStoreId(storeId, pageable));
    }
}
