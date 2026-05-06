package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.entity.MissionStatus;
import com.example.umc10th.domain.mission.dto.request.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionResDTO;

public interface MissionService {

    MissionResDTO.MemberMissionPreviewList getMyMissions(Long memberId, MissionStatus status, Integer page, Integer size);

    MissionResDTO.MissionPreviewList getHomeMissions(Long regionId, Integer page, Integer size);

    MissionResDTO.CompleteResult completeMission(Long memberMissionId, MissionReqDTO.CompleteRequest request);
}
