package com.clubs.aftas.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    @NotNull(message = "Column name cannot be null")
    private String columnName;
    @NotNull(message = "Column value cannot be null")
    private Object columnValue;
}
