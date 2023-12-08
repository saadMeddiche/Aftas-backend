package com.clubs.aftas.dtos.member.requests;

import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

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
    private Member.IdentityDocumentType identityDocument;

    @Column(unique = true)
    @NotNull(message = "Identity number cannot be null")
    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;


}
