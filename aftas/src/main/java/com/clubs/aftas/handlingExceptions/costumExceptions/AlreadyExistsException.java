package com.clubs.aftas.handlingExceptions.costumExceptions;

public class AlreadyExistsException extends RuntimeException {

    private final String error;

    public AlreadyExistsException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }


}