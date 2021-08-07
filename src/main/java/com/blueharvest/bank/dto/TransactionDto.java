package com.blueharvest.bank.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @EqualsAndHashCode.Include
    @ApiModelProperty(name = "id",required = true,dataType = "java.lang.Long")
    private Long id;
    @ApiModelProperty(name = "amount",required = true,dataType = "java.lang.Double")
    private Double amount;
    @ApiModelProperty(name = "transactionType",required = true,dataType = "com.blueharvest.bank.dto.TransactionType")
    private TransactionType transactionType;
    @ApiModelProperty(name = "subAccountId",required = true,dataType = "com.blueharvest.bank.dto.SubAccountDto")
    private SubAccountDto subAccountId;

}
