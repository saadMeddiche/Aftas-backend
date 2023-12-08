package com.clubs.aftas.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ranking extends BaseEntity {

    @Positive(message = "Rank must be positive")
    @NotNull(message = "Rank cannot be null")
    private Integer rank;

    @PositiveOrZero(message = "Score must be positive")
    @NotNull(message = "Score cannot be null")
    private Integer Score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull(message = "Member cannot be null")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @NotNull(message = "Competition cannot be null")
    private Competition competition;
}
