package com.clubs.aftas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String familyName;

    @PastOrPresent(message = "Date of accession must be in the past or present")
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
