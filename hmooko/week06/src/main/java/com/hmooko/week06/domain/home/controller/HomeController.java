package com.hmooko.week06.domain.home.controller;

import com.hmooko.week06.domain.mission.dto.HomeMissionResponse;
import com.hmooko.week06.domain.mission.service.MissionService;
import com.hmooko.week06.global.apiPayload.ApiResponse;
import com.hmooko.week06.global.apiPayload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final MissionService missionService;

    @GetMapping("/missions")
    public ApiResponse<PageResponse<HomeMissionResponse>> getHomeMissions(
            @RequestParam Long userId,
            @RequestParam Long regionId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ApiResponse.onSuccess(missionService.getHomeMissions(userId, regionId, pageable));
    }
}
