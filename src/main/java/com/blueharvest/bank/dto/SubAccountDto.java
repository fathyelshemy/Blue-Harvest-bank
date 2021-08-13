package com.blueharvest.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubAccountDto {
    @EqualsAndHashCode.Include
    @ApiModelProperty(name = "id",required = true,dataType = "java.lang.Long")
    private Long id;
    @ApiModelProperty(name = "balance",required = true,dataType = "java.lang.Double")
    private Double balance;
    @ApiModelProperty(name = "parent",required = true,dataType = "com.blueharvest.bank.dto.CustomerDto")
    private CustomerDto customer;

}
