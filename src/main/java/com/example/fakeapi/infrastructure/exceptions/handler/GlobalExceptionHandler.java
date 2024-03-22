package com.example.fakeapi.infrastructure.exceptions.handler;

import com.example.fakeapi.infrastructure.exceptions.BusinessException;
import com.example.fakeapi.infrastructure.exceptions.ConflictException;
import com.example.fakeapi.infrastructure.exceptions.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handlerUnprocessableEntity(UnprocessableEntityException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handlerConflictException(ConflictException ex, HttpServletRequest request) {
        return response(ex.getMessage(), request, HttpStatus.CONFLICT, LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> response(String message, HttpServletRequest request, HttpStatus status, LocalDateTime date) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, date, status.value(), request.getRequestURI()));
    }
}
