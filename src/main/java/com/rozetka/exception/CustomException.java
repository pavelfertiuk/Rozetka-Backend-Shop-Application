package com.rozetka.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException{

    public static final String EXPIRED_OR_INVALID_TOKEN = "Expired or invalid JWT token";

    private final String message;
    private final HttpStatus httpStatus;

}
