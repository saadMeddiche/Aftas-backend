package com.clubs.aftas.handlingExceptions;

import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.handlingExceptions.costumExceptions.DateValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ExceptionHandlerFactory {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleValidationExceptions(AlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getError() , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DateValidationException.class)
    public ResponseEntity<?> handleDateValidationException(DateValidationException exception) {
        return new ResponseEntity<>(exception.getError() , HttpStatus.BAD_REQUEST);
    }
}
