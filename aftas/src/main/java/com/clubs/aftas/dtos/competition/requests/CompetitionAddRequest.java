package com.clubs.aftas.dtos.competition.requests;

import com.clubs.aftas.entities.Ranking;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CompetitionAddRequest {

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

}
