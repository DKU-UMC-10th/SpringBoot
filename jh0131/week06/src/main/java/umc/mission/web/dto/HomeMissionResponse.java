package umc.mission.web.dto;

import java.time.LocalDate;

public record HomeMissionResponse(
        Long missionId,
        Long storeId,
        String storeName,
        String missionSpec,
        Integer reward,
        LocalDate deadline
) {
}

