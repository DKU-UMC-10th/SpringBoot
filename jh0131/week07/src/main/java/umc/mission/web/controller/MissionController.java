package umc.mission.web.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.mission.domain.MissionStatus;
import umc.mission.service.MissionService;
import umc.mission.web.dto.CursorPageResponse;
import umc.mission.web.dto.HomeMissionResponse;
import umc.mission.web.dto.MemberMissionResponse;
import umc.mission.web.dto.MyMissionRequest;
import umc.mission.web.dto.MyPageResponse;
import umc.mission.web.dto.MyReviewRequest;
import umc.mission.web.dto.MyReviewResponse;
import umc.mission.web.dto.OffsetPageResponse;
import umc.mission.web.dto.ReviewRequest;
import umc.mission.web.dto.ReviewResponse;

@Validated
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/api/stores/{storeId}/reviews")
    public ReviewResponse createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewRequest request
    ) {
        return missionService.createReview(storeId, request);
    }

    @GetMapping("/api/members/{memberId}/my-page")
    public MyPageResponse getMyPage(@PathVariable Long memberId) {
        return missionService.getMyPage(memberId);
    }

    @GetMapping("/api/members/{memberId}/missions")
    public Page<MemberMissionResponse> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam MissionStatus status,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return missionService.getMyMissions(memberId, status, pageable);
    }

    @GetMapping("/api/members/missions/challenging")
    public OffsetPageResponse<MemberMissionResponse> getMyChallengingMissions(
            @Valid @RequestBody MyMissionRequest request,
            @RequestParam(defaultValue = "0") @PositiveOrZero int pageNumber,
            @RequestParam(defaultValue = "10") @Positive int pageSize,
            @RequestParam(defaultValue = "createdAt") String sort
    ) {
        return missionService.getMyChallengingMissions(request, pageNumber, pageSize, sort);
    }

    @GetMapping("/api/members/reviews")
    public CursorPageResponse<MyReviewResponse> getMyReviews(
            @Valid @RequestBody MyReviewRequest request,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") @Positive int size,
            @RequestParam(defaultValue = "ID") String sort
    ) {
        return missionService.getMyReviews(request, cursor, size, sort);
    }

    @GetMapping("/api/home/missions")
    public Page<HomeMissionResponse> getHomeMissions(
            @RequestParam Long memberId,
            @RequestParam Long regionId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return missionService.getHomeMissions(memberId, regionId, pageable);
    }
}
