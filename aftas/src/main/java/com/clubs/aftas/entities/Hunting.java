package com.clubs.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hunting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero(message = "Number of fish must be positive")
    @NotNull(message = "Number of fish cannot be null")
    private Integer numberOfFish;

    @ManyToOne
    @NotNull(message = "Fish cannot be null")
    private Fish fish;

    @ManyToOne
    @NotNull(message = "Member cannot be null")
    @JsonIgnoreProperties("rankings")
    private Member member;

    @ManyToOne
    @NotNull(message = "Competition cannot be null")
    @JsonIgnoreProperties("huntings")
    private Competition competition;
}
