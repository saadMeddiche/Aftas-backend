package com.clubs.aftas.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ranking extends BaseEntity {

    private Integer rank;

    private Integer Score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
