package com.example.onlineAuctionPlatform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuctionExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<AuctionExceptionResponse> handleStudentsException(Exception exception) {
        AuctionExceptionResponse response = new AuctionExceptionResponse(
            HttpStatus.BAD_REQUEST.value(), 
            exception.getMessage(), 
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
