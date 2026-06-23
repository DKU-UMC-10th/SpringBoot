package umc.mission.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewRequest(
        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId,

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "1.0", message = "별점은 1점 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5점 이하이어야 합니다.")
        Float score,

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 500, message = "리뷰 내용은 500자 이하로 입력해주세요.")
        String body
) {
}
