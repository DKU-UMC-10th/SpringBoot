package com.hmooko.week07.domain.user.dto;

public record MyPageResponse(
        Long userId,
        String name,
        String nickname,
        String email,
        String phoneNumber,
        Integer point,
        Long reviewCount,
        Long challengingMissionCount,
        Long completeMissionCount
) {
}
