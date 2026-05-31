package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.dto.request.MemberReqDTO;
import com.example.umc10th.domain.member.dto.response.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberErrorCode;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.region.Region;
import com.example.umc10th.domain.region.RegionRepository;
import com.example.umc10th.global.security.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String singleParameter(String singleParameter) {
        return singleParameter;
    }

    public MemberResDTO.RequestBody requestBody(MemberReqDTO.RequestBody dto) {
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }

    @Transactional
    public MemberResDTO.SignUpResult signUp(MemberReqDTO.SignUp request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        Member member = memberRepository.save(MemberConverter.toMember(request, encodedPassword));
        return MemberConverter.toSignUpResult(member);
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberConverter.toLogin(accessToken);
    }

    public MemberResDTO.GetInfo getInfo(Member member) {
        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.HomeInfo getHomeInfo(Long userId, Long locationId, Integer page, Integer size) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Region region = regionRepository.findById(locationId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        long missionSuccessCount = memberMissionRepository.countByMemberIdAndStatus(userId, MissionStatus.COMPLETE);
        long missionTotalCount = memberMissionRepository.countByMemberId(userId);

        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> missionPage = missionRepository.findAvailableForMember(userId, locationId, pageable);

        return MemberConverter.toHomeInfo(region.getName(), member, missionSuccessCount, missionTotalCount, missionPage);
    }
}
