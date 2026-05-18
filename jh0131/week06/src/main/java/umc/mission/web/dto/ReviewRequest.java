package umc.mission.web.dto;

public record ReviewRequest(
        Long memberId,
        Float score,
        String body
) {
}

