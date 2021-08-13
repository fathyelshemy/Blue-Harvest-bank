package com.blueharvest.bank.entities;

import com.blueharvest.bank.dto.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "amount",nullable = false)
    private double amount;

    @Column(name = "transaction_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_timeStamp",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionTimeStamp;
    @Column(name="status")
    private Boolean status;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "subaccount_id")
    private SubAccount subAccount;

    @ManyToOne(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
