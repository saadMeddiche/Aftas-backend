package com.clubs.aftas.handlingExceptions.costumExceptions;

public class DateValidationException extends RuntimeException {

    private final String error;

    public DateValidationException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}