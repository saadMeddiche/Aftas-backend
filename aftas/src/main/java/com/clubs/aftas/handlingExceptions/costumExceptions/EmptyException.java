package com.clubs.aftas.handlingExceptions.costumExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmptyException extends RuntimeException {
    private final String error;
}
