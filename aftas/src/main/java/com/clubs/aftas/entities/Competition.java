package com.clubs.aftas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotNull(message = "Code cannot be null")
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @NotNull(message = "Number of participants cannot be null")
    @Positive(message = "Number of participants must be positive")
    private Integer numberOfParticipants;

    @NotNull(message = "Location cannot be null")
    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Amount cannot be null")
    @PositiveOrZero(message = "Amount must be positive")
    private Double amount;

    @OneToMany(mappedBy = "competition")
    @NotNull(message = "Rankings cannot be null")
    private List<Ranking> rankings;

}
