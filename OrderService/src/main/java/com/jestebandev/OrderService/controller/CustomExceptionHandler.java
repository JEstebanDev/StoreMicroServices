package com.jestebandev.OrderService.controller;

import com.jestebandev.OrderService.error.CustomErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<Object> handleCustomErrorException(CustomErrorException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private record CustomErrorResponse(String errorCode, String errorMessage) {
    }
}

