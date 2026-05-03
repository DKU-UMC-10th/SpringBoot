package com.hmooko.week05.domain.home.dto;

public record HomeResponse(
        String title,
        String memberName,
        Integer ongoingMissionCount,
        Integer completedMissionCount
) {
}
