package com.example.umc10th.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String email,
            String phoneNumber,
            String password,
            Integer point
    ) {}

    public record RequestBody(
            String stringTest,
            Long longTest
    ) {}

    @Builder
    public record HomeInfo(
            String location,
            Integer point,
            Long missionSuccessCount,
            Long missionTotalCount,
            List<HomeMissionPreview> missionList
    ) {}

    public record SignUpResult(Long memberId) {}

    public record Login(String accessToken) {}

    @Builder
    public record HomeMissionPreview(
            Long missionId,
            String storeName,
            String conditional,
            Integer point,
            LocalDate deadline,
            Long dDay
    ) {}
}
