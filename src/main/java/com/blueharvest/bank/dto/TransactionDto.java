package com.blueharvest.bank.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    @EqualsAndHashCode.Include
    @ApiModelProperty(name = "id",required = true,dataType = "java.lang.Long")
    private Long id;
    @ApiModelProperty(name = "amount",required = true,dataType = "java.lang.Double")
    private Double amount;
    @ApiModelProperty(name = "transactionType",required = true,dataType = "com.blueharvest.bank.dto.TransactionType")
    private TransactionType transactionType;
    @ApiModelProperty(name = "subAccount",required = true,dataType = "com.blueharvest.bank.dto.SubAccountDto")
    @JsonIgnoreProperties({"customer"})
    private SubAccountDto subAccount;
    @ApiModelProperty(name = "customer",required = true,dataType = "com.blueharvest.bank.dto.CustomerDto")
    private CustomerDto customer;
    @ApiModelProperty(name = "transactionTimeStamp",required = true,dataType = "java.util.Date")
    private Date transactionTimeStamp;
    @ApiModelProperty(name = "status",required = true,dataType = "java.lang.Boolean")
    private Boolean status;

}
