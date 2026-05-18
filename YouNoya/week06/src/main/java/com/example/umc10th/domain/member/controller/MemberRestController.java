package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {

    private final MemberService memberService;
    private final MissionService missionService;

    // 회원가입 (5주차 기존 엔드포인트)
    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO request) {
        MemberResponseDTO.JoinResultDTO result = MemberResponseDTO.JoinResultDTO.builder()
                .memberId(1L)
                .createdAt(LocalDateTime.now())
                .build();
        return ApiResponse.onSuccess(result);
    }

    // 마이 페이지
    @GetMapping("/{memberId}")
    public ApiResponse<MemberResponseDTO.MyPageDTO> getMyPage(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberService.getMyPage(memberId));
    }

    // 내가 진행중/완료한 미션 목록 (페이징)
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MissionResponseDTO.UserMissionListDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(missionService.getMyMissions(memberId, status, page));
    }
}
