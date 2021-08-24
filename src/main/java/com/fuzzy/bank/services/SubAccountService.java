package com.fuzzy.bank.services;

import com.fuzzy.bank.exceptions.ResourceNotFoundException;
import com.fuzzy.bank.dto.CustomerDto;
import com.fuzzy.bank.dto.RequestAccountDto;
import com.fuzzy.bank.dto.SubAccountDto;
import com.fuzzy.bank.dto.TransactionDto;
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
