package com.iupv.demo.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class InvalidSignatureError {
    Integer statusCode;
    String message;
    Instant timeStamp;
}
