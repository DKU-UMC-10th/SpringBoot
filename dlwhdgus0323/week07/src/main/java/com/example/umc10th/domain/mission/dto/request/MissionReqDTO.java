package com.example.umc10th.domain.mission.dto.request;

import com.example.umc10th.domain.mission.entity.MissionStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record CompleteRequest(Boolean isComplete) {}

    public record MyMissions(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId
    ) {}
}
