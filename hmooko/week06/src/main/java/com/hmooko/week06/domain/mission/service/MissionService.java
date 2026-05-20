package com.hmooko.week06.domain.mission.service;

import com.hmooko.week06.domain.mission.domain.MissionStatus;
import com.hmooko.week06.domain.mission.dto.HomeMissionResponse;
import com.hmooko.week06.domain.mission.dto.UserMissionPreviewResponse;
import com.hmooko.week06.domain.mission.repository.MissionRepository;
import com.hmooko.week06.domain.mission.repository.UserMissionRepository;
import com.hmooko.week06.domain.user.repository.UserRepository;
import com.hmooko.week06.global.apiPayload.PageResponse;
import com.hmooko.week06.global.apiPayload.code.status.ErrorStatus;
import com.hmooko.week06.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public PageResponse<UserMissionPreviewResponse> getUserMissions(Long userId, MissionStatus status, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new GeneralException(ErrorStatus.NOT_FOUND);
        }

        return PageResponse.from(userMissionRepository.findMissionPageByUserIdAndStatus(userId, status, pageable));
    }

    public PageResponse<HomeMissionResponse> getHomeMissions(Long userId, Long regionId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new GeneralException(ErrorStatus.NOT_FOUND);
        }

        return PageResponse.from(missionRepository.findHomeMissionPage(userId, regionId, pageable));
    }
}
