package com.clubs.aftas.handlingExceptions.costumExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoNotExistException extends RuntimeException{
    private String error;
}
