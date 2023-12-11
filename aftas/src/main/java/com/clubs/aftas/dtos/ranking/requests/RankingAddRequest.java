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

    @NotNull(message = "Member id cannot be null")
    private Long memberId;


    @NotNull(message = "Competition id cannot be null")
    private Long competitionId;
}
