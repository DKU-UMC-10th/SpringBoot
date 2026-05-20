package com.hmooko.week06.domain.user.domain;

import com.hmooko.week06.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDay;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer point;

    @Builder
    public User(
            String name,
            String nickname,
            String email,
            String phoneNumber,
            Gender gender,
            LocalDate birthDay,
            String address,
            Integer point
    ) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthDay = birthDay;
        this.address = address;
        this.point = point;
    }
}
