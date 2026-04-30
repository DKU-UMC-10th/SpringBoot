package com.hmooko.week04.domain.user.domain;

import com.hmooko.week04.global.entity.BaseEntity;
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

    @Column(nullable = false, unique = true)
    private String email;

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
    public User(String email, Gender gender, LocalDate birthDay, String address, Integer point) {
        this.email = email;
        this.gender = gender;
        this.birthDay = birthDay;
        this.address = address;
        this.point = point;
    }
}
