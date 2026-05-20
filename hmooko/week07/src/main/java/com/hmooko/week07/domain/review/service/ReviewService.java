package com.hmooko.week07.domain.review.service;

import com.hmooko.week07.domain.review.domain.Review;
import com.hmooko.week07.domain.review.dto.MyReviewCursorItem;
import com.hmooko.week07.domain.review.dto.MyReviewCursorRequest;
import com.hmooko.week07.domain.review.dto.MyReviewCursorResponse;
import com.hmooko.week07.domain.review.dto.ReviewCreateRequest;
import com.hmooko.week07.domain.review.dto.ReviewCreateResponse;
import com.hmooko.week07.domain.review.dto.StoreReviewResponse;
import com.hmooko.week07.domain.review.repository.ReviewRepository;
import com.hmooko.week07.domain.store.domain.Store;
import com.hmooko.week07.domain.store.repository.StoreRepository;
import com.hmooko.week07.domain.user.domain.User;
import com.hmooko.week07.domain.user.repository.UserRepository;
import com.hmooko.week07.global.apiPayload.PageResponse;
import com.hmooko.week07.global.apiPayload.code.status.ErrorStatus;
import com.hmooko.week07.global.apiPayload.exception.GeneralException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    public MyReviewCursorResponse getMyReviews(MyReviewCursorRequest request) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new GeneralException(ErrorStatus.NOT_FOUND);
        }

        Slice<MyReviewCursorItem> reviewSlice = reviewRepository.findMyReviewSlice(
                request.getUserId(),
                request.getCursorId(),
                PageRequest.of(0, request.getSize())
        );

        List<MyReviewCursorItem> reviews = reviewSlice.getContent();
        Long nextCursor = reviewSlice.hasNext() && !reviews.isEmpty()
                ? reviews.get(reviews.size() - 1).reviewId()
                : null;

        return new MyReviewCursorResponse(reviews, nextCursor, request.getSize(), reviewSlice.hasNext());
    }
}
