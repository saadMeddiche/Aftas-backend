package com.clubs.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
