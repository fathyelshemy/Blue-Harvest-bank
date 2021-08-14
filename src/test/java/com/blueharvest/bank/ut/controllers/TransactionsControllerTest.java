package com.blueharvest.bank.ut.controllers;

import com.blueharvest.bank.controllers.TransactionsController;
import com.blueharvest.bank.repositories.CustomerRepository;
import com.blueharvest.bank.repositories.SubAccountRepository;
import com.blueharvest.bank.repositories.TransactionRepository;
import com.blueharvest.bank.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionsController.class)
class TransactionsControllerTest {

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private SubAccountRepository subAccountRepository;
    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private CustomerRepository customerRepository;


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getTransactionsByCustomerId() throws Exception {
        this.mockMvc.perform(get("/transactions").contentType(MediaType.APPLICATION_JSON).param("customerID",String.valueOf(anyLong())))
                .andExpect(status().isOk());
    }
}