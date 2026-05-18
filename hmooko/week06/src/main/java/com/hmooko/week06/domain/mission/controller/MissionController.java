package com.hmooko.week06.domain.mission.controller;

import com.hmooko.week06.domain.mission.domain.MissionStatus;
import com.hmooko.week06.domain.mission.dto.UserMissionPreviewResponse;
import com.hmooko.week06.domain.mission.service.MissionService;
import com.hmooko.week06.global.apiPayload.ApiResponse;
import com.hmooko.week06.global.apiPayload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/{userId}/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<PageResponse<UserMissionPreviewResponse>> getMissions(
            @PathVariable Long userId,
            @RequestParam MissionStatus status,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ApiResponse.onSuccess(missionService.getUserMissions(userId, status, pageable));
    }
}
