package com.clubs.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Code cannot be null")
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @Future(message = "Date must be in the future")
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
    @Column(nullable = true)
    @JsonIgnoreProperties("competition")
    private List<Ranking> rankings;

}
