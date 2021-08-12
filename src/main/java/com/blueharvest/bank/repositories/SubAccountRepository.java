package com.blueharvest.bank.repositories;

import com.blueharvest.bank.entities.SubAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubAccountRepository extends CrudRepository<SubAccount,Long> {

    @Modifying
    @Query("UPDATE SubAccount subAccount SET subAccount.balance = :balance WHERE subAccount.id= :id")
    SubAccount updateBalanceById(@Param("id") long id,@Param("balance") double balance);
}
