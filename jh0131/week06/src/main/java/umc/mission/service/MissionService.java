package umc.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import umc.mission.web.dto.HomeMissionResponse;
import umc.mission.web.dto.MemberMissionResponse;
import umc.mission.web.dto.MyPageResponse;
import umc.mission.web.dto.ReviewRequest;
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

    public Page<HomeMissionResponse> getHomeMissions(Long memberId, Long regionId, Pageable pageable) {
        return missionRepository.findAvailableMissionsByRegion(memberId, regionId, pageable);
    }
}

