package umc.mission.web.dto;

public record ReviewResponse(
        Long reviewId,
        Long storeId,
        Long memberId,
        Float score,
        String body
) {
}

