package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.response.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getInfo(authMember.getMember()));
    }

    @GetMapping("/{userId}/home")
    public ApiResponse<MemberResDTO.HomeInfo> getHomeInfo(
            @PathVariable Long userId,
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.HOME, memberService.getHomeInfo(userId, locationId, page, size));
    }
}
