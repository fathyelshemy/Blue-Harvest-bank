package com.blueharvest.bank.ut.services;

import com.blueharvest.bank.exceptions.ResourceNotFoundException;
import com.blueharvest.bank.dto.CustomerDto;
import com.blueharvest.bank.entities.Customer;
import com.blueharvest.bank.repositories.CustomerRepository;
import com.blueharvest.bank.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    CustomerDto customerDto=new CustomerDto();
    Customer customer= new Customer();

    @BeforeEach
    void setUp() {
        customerDto.setBalance(5000.0);
        customerDto.setName("fathy");
        customerDto.setSurName("elshemy");
        customerDto.setId(1L);

        customer.setBalance(5000.0);
        customer.setName("fathy");
        customer.setSurName("elshemy");
        customer.setId(1L);

    }

    @Test
    void addCustomer() {

        customerService.addCustomer(customerDto);
        verify(customerRepository).save(customer);
    }

    @Test
    void getCustomer_customerExists() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        CustomerDto customerDto1=customerService.getCustomer(1L);
        assertNotNull(customerDto1);
        assertEquals(1,customerDto1.getId());
        assertEquals("fathy",customerDto1.getName());
        assertEquals("elshemy",customerDto1.getSurName());
        assertEquals(5000.0,customerDto1.getBalance());
    }

    @Test
    void getCustomer_customerNotExists() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows( ResourceNotFoundException.class,() -> customerService.getCustomer(1L));

    }
}