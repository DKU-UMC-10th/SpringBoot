package com.hmooko.week05.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    OTHER("기타");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Gender from(String value) {
        for (Gender gender : values()) {
            if (gender.name().equalsIgnoreCase(value) || gender.displayName.equals(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 성별입니다.");
    }
}
