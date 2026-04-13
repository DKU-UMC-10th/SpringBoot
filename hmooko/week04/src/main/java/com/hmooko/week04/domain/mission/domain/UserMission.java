package com.hmooko.week04.domain.mission.domain;

import com.hmooko.week04.domain.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMission {

    @EmbeddedId
    private UserMissionId id;

    private boolean isComplete;

    @MapsId("missionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserMission(boolean isComplete, Mission mission, User user) {
        this.id = new UserMissionId(mission.getId(), user.getId());
        this.isComplete = isComplete;
        this.mission = mission;
        this.user = user;
    }
}
