package com.blueharvest.bank.repositories;

import com.blueharvest.bank.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {


    List<Transaction> findAllByCustomer_Id(long id);
}
