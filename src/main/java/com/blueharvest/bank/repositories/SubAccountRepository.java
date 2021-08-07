package com.blueharvest.bank.repositories;

import com.blueharvest.bank.entities.SubAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubAccountRepository extends CrudRepository<SubAccount,Long> {
}
