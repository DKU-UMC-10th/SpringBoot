package com.example.umc10th.domain.member.dto.request;

public class MemberReqDTO {
    public record GetInfo(
            String stringTest,
            Long longTest
    ){}
}
