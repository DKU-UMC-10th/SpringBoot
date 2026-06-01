package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MemberRequestDTO {

    public record JoinDTO(
            @NotBlank String name,
            @NotBlank @Email String email,
            @NotBlank String password,
            @NotNull Integer gender,
            @NotNull Integer birthYear,
            @NotNull Integer birthMonth,
            @NotNull Integer birthDay,
            String address,
            String specAddress,
            List<Long> preferCategory
    ) {}

    public record LoginDTO(
            @NotBlank @Email String email,
            @NotBlank String password
    ) {}
}
