package com.clubs.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level extends BaseEntity {

    @Column(unique = true)
    private Integer code;

    private String description;

    @Column(unique = true)
    private Integer points;
}
