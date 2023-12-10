package com.clubs.aftas.dtos.level.requests;

import jakarta.persistence.Column;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LevelRequest {

    @Column(unique = true)
    @Positive(message = "Code must be positive")
    private Integer code;
    private String description;
    @Column(unique = true)
    @Positive(message = "Points must be positive")
    private Integer points;

}
