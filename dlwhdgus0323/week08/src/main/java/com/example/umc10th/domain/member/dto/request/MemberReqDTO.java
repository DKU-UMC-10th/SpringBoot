package com.example.umc10th.domain.member.dto.request;

import com.example.umc10th.domain.member.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ) {}

    public record RequestBody(
            String stringTest,
            Long longTest
    ) {}

    public record SignUp(
            @NotBlank(message = "이름을 입력해주세요.")
            String name,

            @NotBlank(message = "이메일을 입력해주세요.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호를 입력해주세요.")
            @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
            String password,

            String phoneNumber,
            String address,
            String specAddress,
            Integer point,
            Gender gender
    ) {}
}
