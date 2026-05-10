package com.hmooko.week06.domain.mission.dto;

import java.util.List;

public record MissionListResponse(
        List<MissionResponse> missions
) {
}
