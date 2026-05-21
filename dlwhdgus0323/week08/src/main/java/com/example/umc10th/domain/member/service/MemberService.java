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

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
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
