package com.clubs.aftas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    @Column(unique = true)
    private String identityNumber;

    public enum IdentityDocumentType {
        CIN,
        PASSPORT,
        CARTE_RESIDENCE
    }
}
