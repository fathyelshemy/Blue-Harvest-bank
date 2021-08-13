package com.blueharvest.bank.services;

import com.blueharvest.bank.config.exceptions.CreditNotCoveredException;
import com.blueharvest.bank.dto.CustomerDto;
import com.blueharvest.bank.dto.SubAccountDto;
import com.blueharvest.bank.dto.TransactionDto;
import com.blueharvest.bank.dto.TransactionType;
import com.blueharvest.bank.entities.Transaction;
import com.blueharvest.bank.repositories.CustomerRepository;
import com.blueharvest.bank.repositories.SubAccountRepository;
import com.blueharvest.bank.repositories.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final SubAccountRepository subAccountRepository;

    public TransactionService(TransactionRepository transactionRepository,CustomerRepository customerRepository ,SubAccountRepository subAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository= customerRepository;
        this.subAccountRepository=subAccountRepository;
    }

    @Transactional
    public TransactionDto addTransaction(TransactionDto transactionDto) {
        ModelMapper modelMapper= new ModelMapper();
        Transaction transaction= modelMapper.map(transactionDto,Transaction.class);
        Optional.ofNullable(transaction)
                .filter(transaction1 -> transaction1.getCustomer().getBalance()>transaction1.getAmount()).map(transaction1 -> transaction1)
                .orElseThrow(() -> new CreditNotCoveredException("parent account doesn't have enough money",400));
        double parentBalance=calculateBalanceBasedOnTransactionType(TransactionType.WITHDRAWAL, transaction.getCustomer().getBalance(), transaction.getAmount());
        customerRepository.updateAmountById(transaction.getCustomer().getId(),parentBalance);
        transaction.getCustomer().setBalance(parentBalance);
        transaction.getSubAccount().getCustomer().setBalance(parentBalance);
        double childBalance= calculateBalanceBasedOnTransactionType(TransactionType.DEPOSIT, transaction.getSubAccount().getBalance(),transaction.getAmount());

        subAccountRepository.updateBalanceById(transaction.getSubAccount().getId(),childBalance);
        transaction.getSubAccount().setBalance(childBalance);

        Transaction savedTransaction=transactionRepository.save(transaction);
        return modelMapper.map(savedTransaction,TransactionDto.class);
    }

    public List<TransactionDto> retrieveTransactions(long fromId){

        return transactionRepository.findAllByCustomer(fromId).stream()
                .map(transaction -> new ModelMapper().map(transaction,TransactionDto.class))
                .collect(Collectors.toList());
    }

    public TransactionDto buildTransactionObject(double amount, CustomerDto from, SubAccountDto to) {
        return TransactionDto.builder()
                .customer(from)
                .subAccount(to)
                .transactionType(TransactionType.DEPOSIT)
                .transactionTimeStamp(new Date())
                .amount(amount)
                .status(Boolean.TRUE)
                .build();

    }
    private double calculateBalanceBasedOnTransactionType(TransactionType transactionType, double balance , double amount) {
        return Optional.ofNullable(transactionType)
                .filter(transactionType1 -> transactionType1.equals(TransactionType.DEPOSIT)).map(transactionType1 -> balance+amount)
                .orElseGet(()-> balance-amount);
    }
}
