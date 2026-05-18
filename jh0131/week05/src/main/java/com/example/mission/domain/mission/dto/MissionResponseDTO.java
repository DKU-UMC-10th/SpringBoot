package com.example.mission.domain.mission.dto;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    public record MissionPreview(
            Long missionId,
            String title,
            String storeName,
            Integer rewardPoint,
            LocalDate deadline,
            String status
    ) {
    }

    public record MissionListResponse(
            List<MissionPreview> missions
    ) {
    }

    public record CompleteMissionResponse(
            Long userId,
            Long missionId,
            String status
    ) {
    }
}
