package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.request.MemberReqDTO;
import com.example.umc10th.domain.member.dto.response.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpResult> signUp(
            @Valid @RequestBody MemberReqDTO.SignUp request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP, memberService.signUp(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.Login> login(
            @Valid @RequestBody MemberReqDTO.Login request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN, memberService.login(request));
    }
}
