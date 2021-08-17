package com.blueharvest.bank.services;

import com.blueharvest.bank.exceptions.ResourceNotFoundException;
import com.blueharvest.bank.dto.CustomerDto;
import com.blueharvest.bank.dto.RequestAccountDto;
import com.blueharvest.bank.dto.SubAccountDto;
import com.blueharvest.bank.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubAccountService {


    private TransactionService transactionService;
    private CustomerService customerService;

    public SubAccountService(TransactionService transactionService,CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService=customerService;
    }

    public SubAccountDto addSubAccount(RequestAccountDto requestAccountDto){
        CustomerDto parentDto= Optional.ofNullable(customerService.getCustomer(requestAccountDto.getCustomerID()))
                .orElseThrow(()-> new ResourceNotFoundException("Can't find parent account",404));

        SubAccountDto subAccountDto = new SubAccountDto();
        subAccountDto.setCustomer(parentDto);
        subAccountDto.setBalance(requestAccountDto.getInitialCredit());
        TransactionDto transactionDto=transactionService.buildTransactionObject(requestAccountDto.getInitialCredit(),parentDto,subAccountDto);
        transactionDto=transactionService.addTransaction(transactionDto);
        subAccountDto.setCustomer(transactionDto.getCustomer());
        return subAccountDto;
    }

}
