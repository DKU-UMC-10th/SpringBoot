package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MemberRequestDTO {

    public record JoinDTO(
            @NotBlank String name,
            @NotNull Integer gender,
            Integer birthYear,
            Integer birthMonth,
            Integer birthDay,
            String address,
            String specAddress,
            List<Long> preferCategory
    ) {}

    // 내가 진행중인 미션 조회 요청 DTO (Task 1)
    public record MyMissionQueryDTO(
            @NotNull Long memberId,
            @Min(0) Integer page
    ) {}
}
