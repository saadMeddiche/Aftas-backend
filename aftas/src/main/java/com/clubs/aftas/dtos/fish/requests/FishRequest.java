package com.clubs.aftas.dtos.fish.requests;

import com.clubs.aftas.entities.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FishRequest {

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must only contain letters")
    private String name;

    @NotNull(message = "Average weight cannot be null")
    @Positive(message = "Average weight must be positive")
    @Max(value = 20000, message = "Average weight must be less than or equal to 20000")
    private Double averageWeight;

    // Many instances of Fish Class can be associated with one level
    @NotNull(message = "Level id cannot be null")
    private Long levelId;
}
