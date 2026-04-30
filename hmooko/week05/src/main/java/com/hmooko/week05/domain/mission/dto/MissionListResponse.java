package com.hmooko.week05.domain.mission.dto;

import java.util.List;

public record MissionListResponse(
        List<MissionResponse> missions
) {
}
