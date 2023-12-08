package com.clubs.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fish extends BaseEntity {

    @Column(unique = true)
    private String name;

    private Double averageWeight;

    // Many instances of Fish Class can be associated with one level
    @ManyToOne
    private Level level;
}
