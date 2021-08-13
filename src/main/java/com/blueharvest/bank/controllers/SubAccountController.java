package com.blueharvest.bank.controllers;

import com.blueharvest.bank.dto.RequestAccountDto;
import com.blueharvest.bank.services.SubAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subAccount")
public class SubAccountController {

    private SubAccountService subAccountService;

    public SubAccountController(SubAccountService subAccountService) {
        this.subAccountService = subAccountService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSubAccount(@RequestBody RequestAccountDto requestAccountDto) {
        return  new ResponseEntity<>(subAccountService.addSubAccount(requestAccountDto), HttpStatus.OK);
    }
}
