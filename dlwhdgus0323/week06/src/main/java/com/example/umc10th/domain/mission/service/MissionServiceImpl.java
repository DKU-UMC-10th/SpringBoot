package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.MissionStatus;
import com.example.umc10th.domain.mission.dto.request.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionResDTO;
import com.example.umc10th.domain.mission.exception.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MemberMissionPreviewList getMyMissions(Long memberId, MissionStatus status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberIdAndStatus(memberId, status, pageable);
        return MissionConverter.toMemberMissionPreviewList(memberMissionPage);
    }

    @Override
    public MissionResDTO.MissionPreviewList getHomeMissions(Long regionId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> missionPage = missionRepository.findByRegionId(regionId, pageable);
        return MissionConverter.toMissionPreviewList(missionPage);
    }

    @Override
    @Transactional
    public MissionResDTO.CompleteResult completeMission(Long memberMissionId, MissionReqDTO.CompleteRequest request) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        if (Boolean.TRUE.equals(request.isComplete())) {
            memberMission.updateStatus(MissionStatus.COMPLETE);
        }

        return new MissionResDTO.CompleteResult(memberMission.getId());
    }
}
