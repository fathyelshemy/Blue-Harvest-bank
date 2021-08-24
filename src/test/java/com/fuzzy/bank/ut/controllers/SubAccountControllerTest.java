package com.fuzzy.bank.ut.controllers;

import com.fuzzy.bank.controllers.SubAccountController;
import com.fuzzy.bank.dto.RequestAccountDto;
import com.fuzzy.bank.dto.SubAccountDto;
import com.fuzzy.bank.repositories.CustomerRepository;
import com.fuzzy.bank.repositories.SubAccountRepository;
import com.fuzzy.bank.repositories.TransactionRepository;
import com.fuzzy.bank.services.SubAccountService;
import com.fuzzy.bank.utils.TestingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SubAccountController.class)
class SubAccountControllerTest {

    @MockBean
    SubAccountService subAccountService;

    @MockBean
    private SubAccountRepository subAccountRepository;
    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private MockMvc mockMvc;

    RequestAccountDto requestAccountDto;
    @BeforeEach
    void setUp() {
        requestAccountDto= new RequestAccountDto();
        requestAccountDto.setInitialCredit(50.0);
        requestAccountDto.setCustomerID(1L);

    }

    @Test
    void addSubAccount() throws Exception {
        when(subAccountService.addSubAccount(requestAccountDto)).thenReturn(new SubAccountDto());
        String body= TestingUtil.ObjectMapperToString(requestAccountDto);
        this.mockMvc.perform(post("/subAccount").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk());
    }
}