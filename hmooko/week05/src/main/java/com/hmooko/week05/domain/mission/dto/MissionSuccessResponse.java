package com.hmooko.week05.domain.mission.dto;

import java.time.LocalDateTime;

public record MissionSuccessResponse(
        Long missionId,
        String status,
        LocalDateTime completedAt
) {
}
