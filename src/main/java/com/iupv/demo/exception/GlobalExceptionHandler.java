package com.iupv.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {InvalidSignatureException.class})
    public ResponseEntity<InvalidSignatureError> invalidSignature(InvalidSignatureException ex) {
        InvalidSignatureError error = new InvalidSignatureError();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(Instant.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<UserNotFoundError> userNotFound(NoSuchElementException ex) {
        UserNotFoundError error = new UserNotFoundError();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
