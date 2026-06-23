package umc.mission.web.dto;

import jakarta.validation.constraints.NotNull;

public record MyMissionRequest(
        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId
) {
}
