package com.tenalink.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, Object> handle(Exception ex) {
        return Map.of("timestamp", Instant.now(), "status", 400, "error", ex.getMessage());
    }
}
