package umc.mission.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.mission.domain.MissionStatus;
import umc.mission.service.MissionService;
import umc.mission.web.dto.HomeMissionResponse;
import umc.mission.web.dto.MemberMissionResponse;
import umc.mission.web.dto.MyPageResponse;
import umc.mission.web.dto.ReviewRequest;
import umc.mission.web.dto.ReviewResponse;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/api/stores/{storeId}/reviews")
    public ReviewResponse createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequest request
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

    @GetMapping("/api/home/missions")
    public Page<HomeMissionResponse> getHomeMissions(
            @RequestParam Long memberId,
            @RequestParam Long regionId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return missionService.getHomeMissions(memberId, regionId, pageable);
    }
}

