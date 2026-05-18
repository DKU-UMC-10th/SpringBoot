package com.hmooko.week07.domain.mission.dto;

import java.util.List;

public record MissionListResponse(
        List<MissionResponse> missions
) {
}
