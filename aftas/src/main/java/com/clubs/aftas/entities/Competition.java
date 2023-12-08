package com.clubs.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition extends BaseEntity {

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

}
