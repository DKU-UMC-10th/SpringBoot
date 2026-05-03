package com.hmooko.week05.domain.mission.controller;

import com.hmooko.week05.domain.mission.dto.MissionListResponse;
import com.hmooko.week05.domain.mission.dto.MissionResponse;
import com.hmooko.week05.domain.mission.dto.MissionSuccessResponse;
import com.hmooko.week05.global.apiPayload.ApiResponse;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping
    public ApiResponse<MissionListResponse> getMissions(
            @RequestParam
            @Pattern(regexp = "진행중|성공", message = "status는 진행중 또는 성공만 가능합니다.")
            String status
    ) {
        List<MissionResponse> missions = List.of(
                new MissionResponse(
                        1L,
                        "버거하우스",
                        10000,
                        500,
                        LocalDate.now().plusDays(7),
                        status
                )
        );

        return ApiResponse.onSuccess(new MissionListResponse(missions));
    }

    @PatchMapping("/success/{missionId}")
    public ApiResponse<MissionSuccessResponse> completeMission(@PathVariable Long missionId) {
        MissionSuccessResponse response = new MissionSuccessResponse(
                missionId,
                "성공",
                LocalDateTime.now()
        );

        return ApiResponse.onSuccess(response);
    }
}
