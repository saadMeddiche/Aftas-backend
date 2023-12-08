package com.clubs.aftas.entities;

import jakarta.persistence.ManyToOne;

public class Fish {

    private String name;

    private Double averageWeight;

    // Many instances of Fish Class can be associated with one level
    @ManyToOne
    private Level level;
}
