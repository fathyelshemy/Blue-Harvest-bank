package com.fuzzy.bank.services;

import com.fuzzy.bank.exceptions.ResourceNotFoundException;
import com.fuzzy.bank.dto.CustomerDto;
import com.fuzzy.bank.entities.Customer;
import com.fuzzy.bank.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto addCustomer(CustomerDto customerDto){
        Customer customer= new ModelMapper().map(customerDto, Customer.class);
        customerRepository.save(customer);
        return customerDto;
    }

    public CustomerDto getCustomer(long id) {
        return customerRepository.findById(id)
                .map(customer -> new ModelMapper().map(customer,CustomerDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found",404));
    }
}
