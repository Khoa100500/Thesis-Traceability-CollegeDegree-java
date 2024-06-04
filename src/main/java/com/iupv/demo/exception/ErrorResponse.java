package com.iupv.demo.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorResponse {
    Integer statusCode;
    String message;
    Instant timeStamp;
}
