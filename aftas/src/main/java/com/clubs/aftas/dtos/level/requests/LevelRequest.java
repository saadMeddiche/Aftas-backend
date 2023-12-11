package com.clubs.aftas.dtos.level.requests;

import jakarta.persistence.Column;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequest {

    private String description;

    @Column(unique = true)
    @Positive(message = "Points must be positive")
    private Integer points;

}
