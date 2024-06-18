package com.swdeisgn.course.hw4.auth.controllers;

import com.swdeisgn.course.hw4.auth.exceptions.ClientErrorException;
import com.swdeisgn.course.hw4.auth.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthApplicationExecutionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "request validation error");

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<Map<String, String>> handleClientErrorExceptions(ClientErrorException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
