package com.fuzzy.bank.ut.services;

import com.fuzzy.bank.exceptions.CreditNotCoveredException;
import com.blueharvest.bank.dto.*;
import com.fuzzy.bank.entities.Transaction;
import com.fuzzy.bank.repositories.CustomerRepository;
import com.fuzzy.bank.repositories.SubAccountRepository;
import com.fuzzy.bank.repositories.TransactionRepository;
import com.fuzzy.bank.services.TransactionService;
import com.fuzzy.bank.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private SubAccountRepository subAccountRepository;

    @InjectMocks
    private TransactionService transactionService;

    CustomerDto customerDto=new CustomerDto();
    TransactionDto transactionDto= new TransactionDto();
    SubAccountDto subAccountDto= new SubAccountDto();
    RequestAccountDto requestAccountDto= new RequestAccountDto();

    private
    @BeforeEach
    void setUp() {
        customerDto.setBalance(5000.0);
        customerDto.setName("fathy");
        customerDto.setSurName("elshemy");
        customerDto.setId(1L);

        subAccountDto.setCustomer(customerDto);
        subAccountDto.setBalance(500.0);
        subAccountDto.setId(1L);
        transactionDto.setAmount(500.0);
        transactionDto.setSubAccount(subAccountDto);
        transactionDto.setTransactionType(TransactionType.DEPOSIT);
        transactionDto.setCustomer(customerDto);
        transactionDto.setId(1L);
        transactionDto.setStatus(Boolean.TRUE);



        requestAccountDto.setCustomerID(1L);
        requestAccountDto.setInitialCredit(500.0);

    }

    @Test
    void addTransaction_ParentBalanceCoveredTransaction() {
        when(customerRepository.updateAmountById(1L,4500.0)).thenReturn(1);
        when(subAccountRepository.updateBalanceById(1L,500.0)).thenReturn(1);

        Transaction transaction=new ModelMapper().map(transactionDto, Transaction.class);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        TransactionDto transactionDtoResult=transactionService.addTransaction(transactionDto);
        assertNotNull(transactionDtoResult);
        assertEquals(5000.0,transactionDtoResult.getCustomer().getBalance());
        assertEquals(500.0,transactionDtoResult.getAmount());
        assertEquals(500.0,transactionDtoResult.getSubAccount().getBalance());
    }

    @Test
    void addTransaction_ParentBalanceNotCoveredTransaction() {
        customerDto.setBalance(100.0);
        transactionDto.setCustomer(customerDto);
        assertThrows(CreditNotCoveredException.class,()-> transactionService.addTransaction(transactionDto));
    }
    @Test
    void getTransactionsByCustomerID() {
        Transaction transaction= new Transaction();
        transaction.setId(1L);
        when(transactionRepository.findAllByCustomer_Id(anyLong()))
                .thenReturn(Arrays.asList(transaction));
        List<TransactionDto>transactionDtoList=transactionService.getTransactionsByCustomerID(anyLong());
        assertNotNull(transactionDtoList);
    }

    @Test
    void buildTransactionObject() {
        TransactionDto transactionDto=transactionService.buildTransactionObject(500.0,customerDto,subAccountDto);
        assertNotNull(transactionDto);
        assertEquals(500,transactionDto.getAmount());
        assertEquals(TransactionType.DEPOSIT,transactionDto.getTransactionType());
    }
}