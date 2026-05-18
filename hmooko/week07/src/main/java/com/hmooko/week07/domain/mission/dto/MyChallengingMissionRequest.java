package com.hmooko.week07.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyChallengingMissionRequest {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long userId;

    @Min(value = 0, message = "page는 0 이상이어야 합니다.")
    private int page = 0;

    @Min(value = 1, message = "size는 1 이상이어야 합니다.")
    private int size = 10;
}
