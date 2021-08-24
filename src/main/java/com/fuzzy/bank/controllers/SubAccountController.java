package com.fuzzy.bank.controllers;

import com.fuzzy.bank.dto.RequestAccountDto;
import com.fuzzy.bank.dto.ResponseErrorDto;
import com.fuzzy.bank.dto.SubAccountDto;
import com.fuzzy.bank.services.SubAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subAccount")
@Api(value = "Endpoints for Creating subAccount.",tags = {"SubAccount"})
public class SubAccountController {

    private SubAccountService subAccountService;

    public SubAccountController(SubAccountService subAccountService) {
        this.subAccountService = subAccountService;
    }

    @ApiOperation(value = "create SubAccount",tags = {"SubAccount"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful creation", response = SubAccountDto.class),
            @ApiResponse(code = 404,message = "Can't find parent account",response = ResponseErrorDto.class),
            @ApiResponse(code = 400,message = "parent account doesn't have enough money",response = ResponseErrorDto.class),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubAccountDto> addSubAccount(@RequestBody RequestAccountDto requestAccountDto) {
        return  new ResponseEntity<>(subAccountService.addSubAccount(requestAccountDto), HttpStatus.OK);
    }
}
