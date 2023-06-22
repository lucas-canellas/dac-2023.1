package com.dac.cmseventos.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<?> handleNegocioException(DefaultException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem();
        problem.setUserMessage(e.getMessage());
        problem.setStatus(status.value());
        problem.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(400).body(problem);
    }
    
}
