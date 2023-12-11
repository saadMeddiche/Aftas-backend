package com.clubs.aftas.dtos.ranking.requests;

import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingAddRequest {

    @NotNull(message = "Member cannot be null")
    private Member member;


    @NotNull(message = "Competition cannot be null")
    private Competition competition;
}
