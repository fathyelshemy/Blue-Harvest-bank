package com.blueharvest.bank.services;

import com.blueharvest.bank.config.exceptions.ResourceNotFoundException;
import com.blueharvest.bank.dto.CustomerDto;
import com.blueharvest.bank.dto.RequestAccountDto;
import com.blueharvest.bank.dto.SubAccountDto;
import com.blueharvest.bank.dto.TransactionDto;
import com.blueharvest.bank.entities.SubAccount;
import com.blueharvest.bank.repositories.SubAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubAccountService {

    private SubAccountRepository subAccountRepository;

    private TransactionService transactionService;
    private CustomerService customerService;

    public SubAccountService(SubAccountRepository subAccountRepository, TransactionService transactionService,CustomerService customerService) {
        this.subAccountRepository = subAccountRepository;
        this.transactionService = transactionService;
        this.customerService=customerService;
    }

    public SubAccountDto addSubAccount(RequestAccountDto requestAccountDto){
        CustomerDto parentDto= Optional.ofNullable(customerService.getCustomer(requestAccountDto.getCustomerID()))
                .orElseThrow(()-> new ResourceNotFoundException("Can't find parent account",404));

        ModelMapper modelMapper= new ModelMapper();

        SubAccountDto subAccountDto = new SubAccountDto();
        subAccountDto.setCustomer(parentDto);
        subAccountDto.setBalance(requestAccountDto.getInitialCredit());
        SubAccount subAccount= modelMapper.map(subAccountDto,SubAccount.class);
        subAccountRepository.save(subAccount);
        TransactionDto transactionDto=transactionService.buildTransactionObject(requestAccountDto.getInitialCredit(),parentDto,subAccountDto);
        transactionDto=transactionService.addTransaction(transactionDto);
        subAccountDto.setCustomer(transactionDto.getCustomer());
        return subAccountDto;
    }

}
