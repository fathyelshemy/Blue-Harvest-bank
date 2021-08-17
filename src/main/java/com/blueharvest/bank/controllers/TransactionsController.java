package com.blueharvest.bank.controllers;

import com.blueharvest.bank.dto.TransactionDto;
import com.blueharvest.bank.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Api(value = "Endpoints for Retrieve Transaction.",tags = {"Transaction"})
public class TransactionsController {

    private TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "create SubAccount",tags = {"Transaction"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "retrieval successful", response = List.class),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>>getTransactionsByCustomerId(@RequestParam("customerID") long customerID) {

        return new ResponseEntity<>(transactionService.getTransactionsByCustomerID(customerID), HttpStatus.OK);
    }


}
