package com.fuzzy.bank.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing response in case of sending invalid request")
public class ResponseErrorDto {

    private String message;
    private  int code;
}
