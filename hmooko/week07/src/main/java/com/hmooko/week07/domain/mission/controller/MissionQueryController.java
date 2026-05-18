package com.hmooko.week07.domain.mission.controller;

import com.hmooko.week07.domain.mission.dto.MyChallengingMissionRequest;
import com.hmooko.week07.domain.mission.dto.UserMissionPreviewResponse;
import com.hmooko.week07.domain.mission.service.MissionService;
import com.hmooko.week07.global.apiPayload.ApiResponse;
import com.hmooko.week07.global.apiPayload.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionQueryController {

    private final MissionService missionService;

    @PostMapping("/challenging")
    public ApiResponse<PageResponse<UserMissionPreviewResponse>> getChallengingMissions(
            @Valid @RequestBody MyChallengingMissionRequest request
    ) {
        return ApiResponse.onSuccess(
                missionService.getChallengingMissions(request.getUserId(), request.getPage(), request.getSize())
        );
    }
}
