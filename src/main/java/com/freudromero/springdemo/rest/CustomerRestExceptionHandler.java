package com.freudromero.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    // Add an exception handler for CustomerNotFoundException
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {

        // Create CustomerErrorResponse
        CustomerErrorResponse errorResponse = new CustomerErrorResponse(
                                                HttpStatus.NOT_FOUND.value(),
                                                exc.getMessage(),
                                                System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    // Add another exception handler ... to catch any exception (Catch all)
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {

        // Create CustomerErrorResponse
        CustomerErrorResponse errorResponse = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
