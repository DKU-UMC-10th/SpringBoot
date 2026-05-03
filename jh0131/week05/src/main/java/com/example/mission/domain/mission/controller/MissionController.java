package com.example.mission.domain.mission.controller;

import com.example.mission.domain.mission.code.MissionSuccessCode;
import com.example.mission.domain.mission.dto.MissionRequestDTO;
import com.example.mission.domain.mission.dto.MissionResponseDTO;
import com.example.mission.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MissionController {

    @GetMapping("/regions/{regionId}/missions")
    public ResponseEntity<ApiResponse<MissionResponseDTO.MissionListResponse>> getRegionMissions(
            @PathVariable Long regionId
    ) {
        MissionResponseDTO.MissionListResponse response = new MissionResponseDTO.MissionListResponse(
                List.of(
                        new MissionResponseDTO.MissionPreview(
                                1L,
                                "아메리카노 주문하기",
                                "스타벅스 강남점",
                                500,
                                LocalDate.now().plusDays(7),
                                "AVAILABLE"
                        ),
                        new MissionResponseDTO.MissionPreview(
                                2L,
                                "리뷰 작성하기",
                                "맛있는 분식",
                                1000,
                                LocalDate.now().plusDays(10),
                                "AVAILABLE"
                        )
                )
        );

        return ResponseEntity.status(MissionSuccessCode.REGION_MISSIONS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.REGION_MISSIONS, response));
    }

    @GetMapping("/users/{userId}/missions")
    public ResponseEntity<ApiResponse<MissionResponseDTO.MissionListResponse>> getUserMissions(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "IN_PROGRESS") String status
    ) {
        MissionResponseDTO.MissionListResponse response = new MissionResponseDTO.MissionListResponse(
                List.of(
                        new MissionResponseDTO.MissionPreview(
                                3L,
                                "점심 메뉴 인증하기",
                                "든든식당",
                                700,
                                LocalDate.now().plusDays(3),
                                status
                        )
                )
        );

        return ResponseEntity.status(MissionSuccessCode.USER_MISSIONS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.USER_MISSIONS, response));
    }

    @PatchMapping("/users/{userId}/missions/{missionId}")
    public ResponseEntity<ApiResponse<MissionResponseDTO.CompleteMissionResponse>> completeMission(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @Valid @RequestBody MissionRequestDTO.CompleteMissionRequest request
    ) {
        MissionResponseDTO.CompleteMissionResponse response =
                new MissionResponseDTO.CompleteMissionResponse(userId, missionId, request.status());

        return ResponseEntity.status(MissionSuccessCode.COMPLETE_MISSION.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_MISSION, response));
    }
}
