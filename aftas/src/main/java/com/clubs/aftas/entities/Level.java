package com.clubs.aftas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Positive(message = "Code must be positive")
    private Integer code;

    private String description;

    @Column(unique = true)
    @Positive(message = "Points must be positive")
    private Integer points;

    @NotNull(message = "Default level cannot be null")
    private Boolean defaultLevel;
}
