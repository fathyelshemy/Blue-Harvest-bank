package com.fuzzy.bank.controllers.advice;

import com.fuzzy.bank.exceptions.CreditNotCoveredException;
import com.fuzzy.bank.exceptions.ResourceNotFoundException;
import com.fuzzy.bank.dto.ResponseErrorDto;
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
