package com.iupv.demo.exception;

public class InvalidSignatureException extends RuntimeException{
    public InvalidSignatureException(String message) {
        super(message);
    }
}