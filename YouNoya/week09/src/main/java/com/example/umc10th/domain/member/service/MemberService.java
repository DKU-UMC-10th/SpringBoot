package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.Member;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import com.example.umc10th.global.security.auth.AuthMember;
import com.example.umc10th.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResponseDTO.JoinResultDTO join(MemberRequestDTO.JoinDTO request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new GeneralException(ErrorStatus.MEMBER_EMAIL_ALREADY_EXISTS);
        }

        Member member = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .nickname(request.name())
                .gender(toGender(request.gender()))
                .birthYear(request.birthYear())
                .birthMonth(request.birthMonth())
                .birthDay(request.birthDay())
                .address(request.address())
                .specAddress(request.specAddress())
                .build();

        Member savedMember = memberRepository.save(member);

        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(savedMember.getId())
                .createdAt(savedMember.getCreatedAt())
                .build();
    }

    @Transactional
    public MemberResponseDTO.LoginResultDTO login(MemberRequestDTO.LoginDTO request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

        return MemberResponseDTO.LoginResultDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    public MemberResponseDTO.MyPageDTO getMyPage(AuthMember authMember) {
        Member member = authMember.getMember();
        return MemberResponseDTO.MyPageDTO.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .point(member.getPoint())
                .build();
    }

    private Gender toGender(Integer gender) {
        if (gender == null) {
            return Gender.NONE;
        }

        return switch (gender) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            default -> Gender.NONE;
        };
    }
}
