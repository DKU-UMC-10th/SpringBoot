package umc.mission.web.dto;

import java.time.LocalDate;
import umc.mission.domain.MissionStatus;

public record MemberMissionResponse(
        Long memberMissionId,
        Long missionId,
        String storeName,
        String missionSpec,
        Integer reward,
        LocalDate deadline,
        MissionStatus status
) {
}

