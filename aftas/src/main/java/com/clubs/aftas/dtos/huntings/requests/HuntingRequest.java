package com.clubs.aftas.dtos.huntings.requests;

import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.entities.Member;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class HuntingRequest {

    @PositiveOrZero(message = "Number of fish must be positive")
    @NotNull(message = "Number of fish cannot be null")
    private Integer numberOfFish;

    @NotNull(message = "Fish cannot be null")
    private Fish fish;

    @NotNull(message = "Member cannot be null")
    private Member member;

    @NotNull(message = "Competition cannot be null")
    private Competition competition;
}
