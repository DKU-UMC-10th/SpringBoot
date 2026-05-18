package com.example.umc10th.domain.mission.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    public record MissionPreview(
            Long missionId,
            String storeName,
            String missionSpec,
            Integer reward,
            LocalDateTime deadline
    ) {}

    public record MemberMissionPreview(
            Long missionId,
            String storeName,
            String missionSpec,
            Integer reward,
            String status
    ) {}

    public record MissionPreviewList(
            List<MissionPreview> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    public record MemberMissionPreviewList(
            List<MemberMissionPreview> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    public record CompleteResult(Long memberMissionId) {}
}
