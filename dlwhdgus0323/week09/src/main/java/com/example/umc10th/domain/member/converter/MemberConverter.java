package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.request.MemberReqDTO;
import com.example.umc10th.domain.member.entity.Gender;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.dto.response.MemberResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static MemberResDTO.RequestBody toRequestBody(String stringTest, Long longTest) {
        return new MemberResDTO.RequestBody(stringTest, longTest);
    }

    public static Member toMember(MemberReqDTO.SignUp request, String encodedPassword) {
        return Member.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .specAddress(request.specAddress())
                .point(request.point() != null ? request.point() : 0)
                .gender(request.gender() != null ? request.gender() : Gender.NONE)
                .build();
    }

    public static MemberResDTO.SignUpResult toSignUpResult(Member member) {
        return new MemberResDTO.SignUpResult(member.getId());
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return new MemberResDTO.Login(accessToken);
    }

    public static MemberResDTO.HomeMissionPreview toHomeMissionPreview(Mission mission) {
        LocalDate deadline = mission.getDeadline().toLocalDate();
        long dDay = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
        return MemberResDTO.HomeMissionPreview.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .conditional(mission.getMissionSpec())
                .point(mission.getReward())
                .deadline(deadline)
                .dDay(dDay)
                .build();
    }

    public static MemberResDTO.HomeInfo toHomeInfo(
            String location,
            Member member,
            long missionSuccessCount,
            long missionTotalCount,
            Page<Mission> missionPage
    ) {
        List<MemberResDTO.HomeMissionPreview> missionList = missionPage.stream()
                .map(MemberConverter::toHomeMissionPreview)
                .collect(Collectors.toList());

        return MemberResDTO.HomeInfo.builder()
                .location(location)
                .point(member.getPoint())
                .missionSuccessCount(missionSuccessCount)
                .missionTotalCount(missionTotalCount)
                .missionList(missionList)
                .build();
    }
}
