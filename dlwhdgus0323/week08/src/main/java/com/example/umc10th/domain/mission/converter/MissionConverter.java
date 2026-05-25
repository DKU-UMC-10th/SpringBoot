package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.dto.response.MissionResDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionPreview toMissionPreview(Mission mission) {
        return new MissionResDTO.MissionPreview(
                mission.getId(),
                mission.getStore().getName(),
                mission.getMissionSpec(),
                mission.getReward(),
                mission.getDeadline()
        );
    }

    public static MissionResDTO.MemberMissionPreview toMemberMissionPreview(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return new MissionResDTO.MemberMissionPreview(
                mission.getId(),
                mission.getStore().getName(),
                mission.getMissionSpec(),
                mission.getReward(),
                memberMission.getStatus().name()
        );
    }

    public static MissionResDTO.MissionPreviewList toMissionPreviewList(Page<Mission> missionPage) {
        List<MissionResDTO.MissionPreview> missionList = missionPage.stream()
                .map(MissionConverter::toMissionPreview)
                .collect(Collectors.toList());

        return new MissionResDTO.MissionPreviewList(
                missionList,
                missionList.size(),
                missionPage.getTotalPages(),
                missionPage.getTotalElements(),
                missionPage.isFirst(),
                missionPage.isLast()
        );
    }

    public static MissionResDTO.MemberMissionPreviewList toMemberMissionPreviewList(Page<MemberMission> memberMissionPage) {
        List<MissionResDTO.MemberMissionPreview> missionList = memberMissionPage.stream()
                .map(MissionConverter::toMemberMissionPreview)
                .collect(Collectors.toList());

        return new MissionResDTO.MemberMissionPreviewList(
                missionList,
                missionList.size(),
                memberMissionPage.getTotalPages(),
                memberMissionPage.getTotalElements(),
                memberMissionPage.isFirst(),
                memberMissionPage.isLast()
        );
    }
}
