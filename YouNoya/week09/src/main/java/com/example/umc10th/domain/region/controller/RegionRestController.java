package com.example.umc10th.domain.region.controller;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/regions")
public class RegionRestController {

    private final MissionService missionService;

    // 홈 화면: 선택된 지역에서 도전 가능한 미션 목록 (페이징)
    @GetMapping("/{regionId}/missions")
    public ApiResponse<MissionResponseDTO.AvailableMissionListDTO> getAvailableMissions(
            @PathVariable Long regionId,
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(missionService.getAvailableMissions(regionId, memberId, page));
    }
}
