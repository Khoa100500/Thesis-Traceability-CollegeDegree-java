package com.iupv.demo.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class UserNotFoundError {
        Integer statusCode;
        String message;
        Instant timeStamp;
}
