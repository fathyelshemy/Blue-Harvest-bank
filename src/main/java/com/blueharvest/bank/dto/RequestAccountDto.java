package com.blueharvest.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Class request to create sub-account with initial amount")
public class RequestAccountDto {
    @EqualsAndHashCode.Include
    @ApiModelProperty(name = "customerID",required = true,dataType = "java.lang.Long")
    @JsonProperty("customerID")
    private Long customerID;
    @ApiModelProperty(name = "initialCredit",required = true,dataType = "java.lang.Double")
    @JsonProperty("initialCredit")
    private Double initialCredit;

}
