package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.auth.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {

    private final MemberService memberService;
    private final MissionService missionService;

    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        return ApiResponse.onSuccess(memberService.join(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginDTO request) {
        return ApiResponse.onSuccess(memberService.login(request));
    }

    @GetMapping("/my")
    public ApiResponse<MemberResponseDTO.MyPageDTO> getMyPage(@AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.onSuccess(memberService.getMyPage(authMember));
    }

    @GetMapping("/{memberId}/missions")
    public ApiResponse<MissionResponseDTO.UserMissionListDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(missionService.getMyMissions(memberId, status, page));
    }
}
