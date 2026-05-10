package com.hmooko.week06.domain.mission.dto;

import java.time.LocalDate;

public record MissionResponse(
        Long missionId,
        String storeName,
        Integer accomplishedAmount,
        Integer accumulationPoint,
        LocalDate deadline,
        String status
) {
}
