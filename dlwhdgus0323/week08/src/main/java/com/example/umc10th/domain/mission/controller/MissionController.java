package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.entity.MissionStatus;
import com.example.umc10th.domain.mission.dto.request.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionResDTO;
import com.example.umc10th.domain.mission.exception.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/members/{memberId}")
    public ApiResponse<MissionResDTO.MemberMissionPreviewList> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MEMBER_MISSIONS,
                missionService.getMyMissions(memberId, status, page, size));
    }

    @PostMapping("/my")
    public ApiResponse<MissionResDTO.MemberMissionPreviewList> getMyMissionsFromBody(
            @RequestBody @Valid MissionReqDTO.MyMissions request,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MY_MISSIONS,
                missionService.getMyMissionsFromBody(request.userId(), status, page, size));
    }

    @GetMapping
    public ApiResponse<MissionResDTO.MissionPreviewList> getHomeMissions(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.GET_HOME_MISSIONS,
                missionService.getHomeMissions(regionId, page, size));
    }

    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MissionResDTO.CompleteResult> completeMission(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.CompleteRequest request
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_MISSION,
                missionService.completeMission(memberMissionId, request));
    }
}
