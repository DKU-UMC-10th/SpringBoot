package umc.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mission.domain.Member;
import umc.mission.domain.MissionStatus;
import umc.mission.domain.Review;
import umc.mission.domain.Store;
import umc.mission.repository.MemberMissionRepository;
import umc.mission.repository.MemberRepository;
import umc.mission.repository.MissionRepository;
import umc.mission.repository.ReviewRepository;
import umc.mission.repository.StoreRepository;
import umc.mission.web.dto.CursorPageResponse;
import umc.mission.web.dto.HomeMissionResponse;
import umc.mission.web.dto.MemberMissionResponse;
import umc.mission.web.dto.MyMissionRequest;
import umc.mission.web.dto.MyPageResponse;
import umc.mission.web.dto.MyReviewRequest;
import umc.mission.web.dto.MyReviewResponse;
import umc.mission.web.dto.OffsetPageResponse;
import umc.mission.web.dto.ReviewRequest;
import umc.mission.web.dto.ReviewCursorSort;
import umc.mission.web.dto.ReviewResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public ReviewResponse createReview(Long storeId, ReviewRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .score(request.score())
                .body(request.body())
                .build();

        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                store.getId(),
                member.getId(),
                savedReview.getScore(),
                savedReview.getBody()
        );
    }

    public MyPageResponse getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return new MyPageResponse(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getPoint(),
                reviewRepository.countByMemberId(memberId),
                memberMissionRepository.countByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING),
                memberMissionRepository.countByMemberIdAndStatus(memberId, MissionStatus.COMPLETE)
        );
    }

    public Page<MemberMissionResponse> getMyMissions(Long memberId, MissionStatus status, Pageable pageable) {
        return memberMissionRepository.findMemberMissions(memberId, status, pageable);
    }

    public OffsetPageResponse<MemberMissionResponse> getMyChallengingMissions(
            MyMissionRequest request,
            int pageNumber,
            int pageSize,
            String sort
    ) {
        validateMemberExists(request.memberId());
        String sortProperty = resolveMemberMissionSort(sort);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sortProperty));

        Page<MemberMissionResponse> missions = memberMissionRepository.findMemberMissions(
                request.memberId(),
                MissionStatus.CHALLENGING,
                pageable
        );
        return OffsetPageResponse.from(missions);
    }

    public CursorPageResponse<MyReviewResponse> getMyReviews(
            MyReviewRequest request,
            String cursor,
            int size,
            String sort
    ) {
        validateMemberExists(request.memberId());
        ReviewCursorSort cursorSort = resolveReviewCursorSort(sort);
        Pageable pageable = PageRequest.of(0, size);

        Slice<MyReviewResponse> reviews = switch (cursorSort) {
            case ID -> reviewRepository.findMyReviewsByIdCursor(
                    request.memberId(),
                    parseIdCursor(cursor),
                    pageable
            );
            case SCORE -> {
                ScoreCursor scoreCursor = parseScoreCursor(cursor);
                yield reviewRepository.findMyReviewsByScoreCursor(
                        request.memberId(),
                        scoreCursor.score(),
                        scoreCursor.reviewId(),
                        pageable
                );
            }
        };

        return new CursorPageResponse<>(
                reviews.getContent(),
                reviews.hasNext(),
                createNextCursor(reviews, cursorSort),
                reviews.getNumberOfElements()
        );
    }

    public Page<HomeMissionResponse> getHomeMissions(Long memberId, Long regionId, Pageable pageable) {
        return missionRepository.findAvailableMissionsByRegion(memberId, regionId, pageable);
    }

    private void validateMemberExists(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
    }

    private String resolveMemberMissionSort(String sort) {
        if ("id".equalsIgnoreCase(sort)) {
            return "id";
        }
        return "createdAt";
    }

    private ReviewCursorSort resolveReviewCursorSort(String sort) {
        if ("score".equalsIgnoreCase(sort) || "star".equalsIgnoreCase(sort)) {
            return ReviewCursorSort.SCORE;
        }
        if ("id".equalsIgnoreCase(sort)) {
            return ReviewCursorSort.ID;
        }
        throw new IllegalArgumentException("리뷰 정렬 기준은 ID 또는 SCORE만 가능합니다.");
    }

    private Long parseIdCursor(String cursor) {
        if (cursor == null || cursor.isBlank() || "-1".equals(cursor)) {
            return null;
        }

        String[] tokens = cursor.split(":");
        return Long.parseLong(tokens[tokens.length - 1]);
    }

    private ScoreCursor parseScoreCursor(String cursor) {
        if (cursor == null || cursor.isBlank() || "-1".equals(cursor)) {
            return new ScoreCursor(null, null);
        }

        String[] tokens = cursor.split(":");
        if (tokens.length < 2) {
            throw new IllegalArgumentException("별점 커서는 SCORE:score:reviewId 형식이어야 합니다.");
        }

        return new ScoreCursor(
                Float.parseFloat(tokens[tokens.length - 2]),
                Long.parseLong(tokens[tokens.length - 1])
        );
    }

    private String createNextCursor(Slice<MyReviewResponse> reviews, ReviewCursorSort sort) {
        if (!reviews.hasNext() || reviews.isEmpty()) {
            return null;
        }

        MyReviewResponse lastReview = reviews.getContent().get(reviews.getNumberOfElements() - 1);
        if (sort == ReviewCursorSort.SCORE) {
            return "SCORE:" + lastReview.score() + ":" + lastReview.reviewId();
        }
        return "ID:" + lastReview.reviewId();
    }

    private record ScoreCursor(Float score, Long reviewId) {
    }
}
