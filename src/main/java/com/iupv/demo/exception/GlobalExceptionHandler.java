package com.iupv.demo.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {InvalidSignatureException.class})
    public ResponseEntity<ErrorResponse> invalidSignature(InvalidSignatureException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(Instant.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> userNotFound(NoSuchElementException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorResponse> invalidJwt(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        error.setTimeStamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
