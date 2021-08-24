package com.fuzzy.bank.repositories;

import com.fuzzy.bank.entities.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Modifying
    @Query("update Customer customer set customer.balance = :balance where customer.id= :id")
    int updateAmountById(@Param("id") long id, @Param("balance") double balance);
}
