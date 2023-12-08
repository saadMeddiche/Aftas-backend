package com.clubs.aftas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must only contain letters")
    private String name;

    @NotNull(message = "Family name cannot be null")
    @NotBlank(message = "Family name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Family name must only contain letters")
    private String familyName;

    @PastOrPresent(message = "Date of accession must be in the past or present")
    @NotNull(message = "Date of accession cannot be null")
    private LocalDate accessionDate;

    @NotNull(message = "Nationality cannot be null")
    @NotBlank(message = "Nationality cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nationality must only contain letters")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @Column(unique = true)
    @NotNull(message = "Identity number cannot be null")
    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @NotNull(message = "Rankings cannot be null")
    @NotEmpty(message = "Rankings cannot be empty")
    private List<Ranking> rankings;

    public enum IdentityDocumentType {
        CIN,
        PASSPORT,
        CARTE_RESIDENCE
    }
}
