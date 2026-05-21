package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberErrorCode;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.dto.request.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewResDTO;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreErrorCode;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public ReviewResDTO.WriteReview writeReview(Long userId, ReviewReqDTO.WriteReview request) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        Review review = ReviewConverter.toReview(request, member, store);
        reviewRepository.save(review);

        if (request.missionId() != null) {
            Mission mission = missionRepository.findById(request.missionId())
                    .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));
            MemberMission memberMission = MemberMission.builder()
                    .member(member)
                    .mission(mission)
                    .build();
            memberMissionRepository.save(memberMission);
        }

        return ReviewConverter.toWriteReviewResponse(review);
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResDTO.ReviewCursorResult getMyReviews(Long userId, String cursor, String sort, Integer size) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Review> slice;

        // 커서 파싱: 커서가 없거나 "ID" 정렬
        if ("STAR".equalsIgnoreCase(sort)) {
            if (cursor == null) {
                // 별점순 첫 페이지
                slice = reviewRepository.findByMemberIdOrderByScoreDescIdDesc(userId, pageable);
            } else {
                // 커서 형식: STAR:{score}:{id}
                String[] parts = cursor.split(":");
                Float cursorScore = Float.parseFloat(parts[1]);
                Long cursorId = Long.parseLong(parts[2]);
                slice = reviewRepository.findByMemberIdWithStarCursor(userId, cursorScore, cursorId, pageable);
            }
        } else {
            // ID 정렬 (기본값)
            if (cursor == null) {
                // ID순 첫 페이지
                slice = reviewRepository.findByMemberIdOrderByIdDesc(userId, pageable);
            } else {
                // 커서 형식: ID:{id}
                String[] parts = cursor.split(":");
                Long cursorId = Long.parseLong(parts[1]);
                slice = reviewRepository.findByMemberIdAndIdLessThan(userId, cursorId, pageable);
            }
        }

        return ReviewConverter.toReviewCursorResult(slice, sort);
    }
}
