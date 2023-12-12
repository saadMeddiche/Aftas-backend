package com.clubs.aftas.dtos.competition;

import com.clubs.aftas.entities.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Top {

    @JsonIgnoreProperties("rankings")
    private Member member;

    private Integer score;

}
