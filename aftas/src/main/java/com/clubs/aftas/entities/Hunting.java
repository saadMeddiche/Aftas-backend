package com.clubs.aftas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class Hunting extends BaseEntity {

    private Integer numberOfFish;

    @ManyToOne
    private Fish fish;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Competition competition;
}
