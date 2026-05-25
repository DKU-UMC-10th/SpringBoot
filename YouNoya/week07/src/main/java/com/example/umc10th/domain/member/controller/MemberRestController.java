package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {

    private final MemberService memberService;
    private final MissionService missionService;
    private final ReviewService reviewService;

    // 회원가입 (5주차 기존 엔드포인트) + @Valid 추가 (Task 3)
    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(
            @Valid @RequestBody MemberRequestDTO.JoinDTO request) {
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

    // 기존: 내가 진행중/완료한 미션 목록 (PathVariable 방식 유지)
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MissionResponseDTO.UserMissionListDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(missionService.getMyMissions(memberId, status, page));
    }

    // Task 1: 내가 진행중인 미션 조회 (오프셋 기반, memberId는 Request Body에서 수신)
    @PostMapping("/missions/challenging")
    public ApiResponse<MissionResponseDTO.UserMissionListDTO> getMyChallengingMissions(
            @Valid @RequestBody MemberRequestDTO.MyMissionQueryDTO request
    ) {
        int page = request.page() != null ? request.page() : 0;
        return ApiResponse.onSuccess(
                missionService.getMyMissions(request.memberId(), MissionStatus.CHALLENGING, page));
    }

    // Task 2: 내가 생성한 리뷰 목록 (커서 기반)
    // sort: ID(기본, id 내림차순) / RATING(별점 내림차순)
    // cursor: ID 순 → "ID:733" / 별점 순 → "RATING:4.0:733"
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewCursorListDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ID") String sort
    ) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(memberId, cursor, size, sort));
    }
}
