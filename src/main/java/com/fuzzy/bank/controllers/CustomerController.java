package com.fuzzy.bank.controllers;

import com.fuzzy.bank.dto.CustomerDto;
import com.fuzzy.bank.dto.ResponseErrorDto;
import com.fuzzy.bank.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Api(value = "Endpoints for Creating, Retrieving Customer.",tags = {"Customer"})
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "create Customer",tags = {"Customer"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful creation", response = CustomerDto.class)
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.OK);
    }

    @ApiOperation(value = "get Customer",tags = {"Customer"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "retrieve customer", response = CustomerDto.class),
            @ApiResponse(code = 404,message = "Customer Not Found",response = ResponseErrorDto.class)
    })

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomer(@RequestParam("customerID") long id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }
}
