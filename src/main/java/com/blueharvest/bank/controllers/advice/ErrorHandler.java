package com.blueharvest.bank.controllers.advice;

import com.blueharvest.bank.exceptions.CreditNotCoveredException;
import com.blueharvest.bank.exceptions.ResourceNotFoundException;
import com.blueharvest.bank.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return new ResponseEntity<>(new ResponseErrorDto(resourceNotFoundException.getMessage(),resourceNotFoundException.getCode()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CreditNotCoveredException.class)
    public ResponseEntity<ResponseErrorDto> handleCreditNotCoveredException(CreditNotCoveredException creditNotCoveredException){
        return new ResponseEntity<>(new ResponseErrorDto(creditNotCoveredException.getMessage(),creditNotCoveredException.getCode()), HttpStatus.BAD_REQUEST);
    }
}
