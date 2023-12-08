package com.clubs.aftas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hunting extends BaseEntity {

    @PositiveOrZero(message = "Number of fish must be positive")
    @NotNull(message = "Number of fish cannot be null")
    private Integer numberOfFish;

    @ManyToOne
    @NotNull(message = "Fish cannot be null")
    private Fish fish;

    @ManyToOne
    @NotNull(message = "Member cannot be null")
    private Member member;

    @ManyToOne
    @NotNull(message = "Competition cannot be null")
    private Competition competition;
}
