package com.blueharvest.bank.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    @Getter
    private String message;
}
