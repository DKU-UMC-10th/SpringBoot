package com.hmooko.week04.domain.inquiry.domain;

import com.hmooko.week04.domain.user.domain.User;
import com.hmooko.week04.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "inquiry")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Inquiry(String name, LocalDate date, User user) {
        this.name = name;
        this.date = date;
        this.user = user;
    }
}
