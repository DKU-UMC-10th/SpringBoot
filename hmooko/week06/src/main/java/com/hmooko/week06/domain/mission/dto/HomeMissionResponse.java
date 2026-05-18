package com.hmooko.week06.domain.mission.dto;

import java.time.LocalDate;

public record HomeMissionResponse(
        Long missionId,
        Long storeId,
        String storeName,
        String missionSpec,
        Integer rewardPoint,
        LocalDate deadline
) {
}
