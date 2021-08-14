package com.blueharvest.bank.ut.controllers;

import com.blueharvest.bank.controllers.CustomerController;
import com.blueharvest.bank.dto.CustomerDto;
import com.blueharvest.bank.repositories.CustomerRepository;
import com.blueharvest.bank.repositories.SubAccountRepository;
import com.blueharvest.bank.repositories.TransactionRepository;
import com.blueharvest.bank.services.CustomerService;
import com.blueharvest.bank.utils.TestingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;
    @MockBean
    private SubAccountRepository subAccountRepository;
    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private MockMvc mockMvc;

    CustomerDto customerDto;
    @BeforeEach
    void setUp() {
        customerDto= new CustomerDto();
        customerDto.setBalance(1500.0);
        customerDto.setSurName("elshemy");
        customerDto.setName("fathy");
    }

    @Test
    void addCustomer() throws Exception {
        when(customerService.addCustomer(customerDto)).thenReturn(customerDto);
        String body= TestingUtil.ObjectMapperToString(customerDto);
        this.mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomer() throws Exception {
        when(customerService.getCustomer(anyLong())).thenReturn(customerDto);
        this.mockMvc.perform(get("/customer").contentType(MediaType.APPLICATION_JSON).param("customerID", String.valueOf(anyLong())))
        .andExpect(status().isOk());
    }
}