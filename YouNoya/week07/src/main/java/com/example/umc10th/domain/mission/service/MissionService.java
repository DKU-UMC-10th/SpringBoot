package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.Mission;
import com.example.umc10th.domain.mission.UserMission;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    // 내가 진행중/완료한 미션 목록 (페이징)
    public MissionResponseDTO.UserMissionListDTO getMyMissions(Long memberId,
                                                               MissionStatus status,
                                                               Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<UserMission> userMissions = userMissionRepository.findByMemberIdAndStatus(memberId, status, pageRequest);

        List<MissionResponseDTO.UserMissionDTO> dtoList = userMissions.getContent().stream()
                .map(um -> MissionResponseDTO.UserMissionDTO.builder()
                        .userMissionId(um.getId())
                        .missionId(um.getMission().getId())
                        .storeName(um.getMission().getStore().getName())
                        .missionTitle(um.getMission().getTitle())
                        .reward(um.getMission().getReward())
                        .deadline(um.getMission().getDeadline())
                        .status(um.getStatus())
                        .completedAt(um.getCompletedAt())
                        .build())
                .toList();

        return MissionResponseDTO.UserMissionListDTO.builder()
                .missionList(dtoList)
                .listSize(dtoList.size())
                .totalPage(userMissions.getTotalPages())
                .totalElements(userMissions.getTotalElements())
                .isFirst(userMissions.isFirst())
                .isLast(userMissions.isLast())
                .build();
    }

    // 홈 화면: 선택된 지역에서 도전 가능한 미션 목록 (페이징)
    public MissionResponseDTO.AvailableMissionListDTO getAvailableMissions(Long regionId,
                                                                           Long memberId,
                                                                           Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(
                regionId, memberId, MissionStatus.CHALLENGING, pageRequest);

        List<MissionResponseDTO.AvailableMissionDTO> dtoList = missions.getContent().stream()
                .map(m -> MissionResponseDTO.AvailableMissionDTO.builder()
                        .missionId(m.getId())
                        .storeName(m.getStore().getName())
                        .missionTitle(m.getTitle())
                        .reward(m.getReward())
                        .deadline(m.getDeadline())
                        .build())
                .toList();

        return MissionResponseDTO.AvailableMissionListDTO.builder()
                .missionList(dtoList)
                .listSize(dtoList.size())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
}
