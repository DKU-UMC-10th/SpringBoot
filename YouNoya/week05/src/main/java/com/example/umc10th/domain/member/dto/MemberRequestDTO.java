package com.example.umc10th.domain.member.dto;

import java.util.List;

public class MemberRequestDTO {

    public record JoinDTO(
            String name,
            Integer gender,
            Integer birthYear,
            Integer birthMonth,
            Integer birthDay,
            String address,
            String specAddress,
            List<Long> preferCategory
    ) {}
}
