package com.example.mission.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;

public class MissionRequestDTO {

    public record CompleteMissionRequest(
            @NotBlank(message = "미션 상태는 필수입니다.")
            String status
    ) {
    }
}
