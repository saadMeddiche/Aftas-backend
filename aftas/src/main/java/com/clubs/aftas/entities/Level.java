package com.clubs.aftas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level extends BaseEntity {

    @Column(unique = true)
    @Positive(message = "Code must be positive")
    private Integer code;

    private String description;

    @Column(unique = true)
    @Positive(message = "Points must be positive")
    private Integer points;
}
