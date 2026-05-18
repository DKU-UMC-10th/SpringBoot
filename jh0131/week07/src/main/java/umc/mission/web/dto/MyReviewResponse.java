package umc.mission.web.dto;

public record MyReviewResponse(
        Long reviewId,
        String storeName,
        Float score,
        String body
) {
}
