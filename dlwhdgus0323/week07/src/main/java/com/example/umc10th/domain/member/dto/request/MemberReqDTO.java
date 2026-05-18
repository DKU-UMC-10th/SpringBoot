package com.example.umc10th.domain.member.dto.request;

import com.example.umc10th.domain.member.entity.Gender;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ) {}

    public record RequestBody(
            String stringTest,
            Long longTest
    ) {}

    public record SignUp(
            String name,
            String email,
            String phoneNumber,
            String address,
            String specAddress,
            Integer point,
            Gender gender
    ) {}
}
