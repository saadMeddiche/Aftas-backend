package com.clubs.aftas.handlingExceptions;

import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.handlingExceptions.costumExceptions.DateValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleDHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        if(exception.getMessage().contains("not one of the values accepted for Enum class: [CIN, PASSPORT, CARTE_RESIDENCE]")){
            return new ResponseEntity<>("Invalid identityDocument value it shoudld be [CIN ,PASSPORT,CARTE_RESIDENCE]" , HttpStatus.BAD_REQUEST);
        }

        if(exception.getMessage().contains("Failed to deserialize java.time.LocalDate")){
            return new ResponseEntity<>("Respect the format YYYY-MM-DD" , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
    }

    // Thanks To Yassine Sahyane
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleDateMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }
}
