package com.hmooko.week05.domain.mission.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMissionId implements Serializable {

    @Column(name = "mission_id")
    private Long missionId;

    @Column(name = "user_id")
    private Long userId;

    public UserMissionId(Long missionId, Long userId) {
        this.missionId = missionId;
        this.userId = userId;
    }
}
