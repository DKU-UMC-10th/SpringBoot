package com.hmooko.week07.domain.mission.dto;

import com.hmooko.week07.domain.mission.domain.MissionStatus;
import java.time.LocalDate;

public record UserMissionPreviewResponse(
        Long userMissionId,
        Long missionId,
        String storeName,
        String missionSpec,
        Integer rewardPoint,
        LocalDate deadline,
        MissionStatus status
) {
}
