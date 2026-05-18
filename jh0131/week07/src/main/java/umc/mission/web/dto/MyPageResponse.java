package umc.mission.web.dto;

public record MyPageResponse(
        Long memberId,
        String nickname,
        String email,
        String phoneNumber,
        Integer point,
        long reviewCount,
        long challengingMissionCount,
        long completeMissionCount
) {
}

