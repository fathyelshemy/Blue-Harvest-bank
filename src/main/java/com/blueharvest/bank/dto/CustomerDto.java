package com.blueharvest.bank.dto;

import com.blueharvest.bank.entities.SubAccount;
import com.blueharvest.bank.entities.Transaction;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @EqualsAndHashCode.Include
    @ApiModelProperty(name = "id",required = true,dataType = "java.lang.Long")
    private Long id;
    @ApiModelProperty(name = "name",required = true,dataType = "java.lang.String")
    private String name;
    @ApiModelProperty(name = "surName",required = true,dataType = "java.lang.String")
    private String surName;
    @ApiModelProperty(name = "balance",required = true,dataType = "java.lang.Double")
    private Double balance;
    @ApiModelProperty(name = "transactions",required = true,dataType = "java.util.List")
    private List<Transaction> transactions;
    @ApiModelProperty(name = "childAccounts",required = true,dataType = "java.util.List")
    private List<SubAccount> childAccounts;

}
