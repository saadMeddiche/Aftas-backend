package com.clubs.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
