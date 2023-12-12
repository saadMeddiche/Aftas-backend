package com.clubs.aftas.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Rank must be positive")
    private Integer rank;

    @PositiveOrZero(message = "Score must be positive")
    @NotNull(message = "Score cannot be null")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull(message = "Member cannot be null")
    @JsonIgnoreProperties({"rankings"})
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @NotNull(message = "Competition cannot be null")
    @JsonIgnoreProperties({"rankings" , "huntings"})
    private Competition competition;
}
