package com.blueharvest.bank.controllers;

import com.blueharvest.bank.dto.TransactionDto;
import com.blueharvest.bank.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {

    private TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>>getTransactionsByCustomerId(@RequestParam("customerID") long customerID) {

        return new ResponseEntity<>(transactionService.getTransactionsByCustomerID(customerID), HttpStatus.OK);
    }


}
