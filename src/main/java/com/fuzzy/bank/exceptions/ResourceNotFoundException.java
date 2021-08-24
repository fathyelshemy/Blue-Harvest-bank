package com.fuzzy.bank.exceptions;

import lombok.*;

@Getter
@NoArgsConstructor
public final class ResourceNotFoundException extends RuntimeException {

    private int code;


    public ResourceNotFoundException(String message,int code){
        super(message);
        this.code=code;
    }
    public ResourceNotFoundException(int code){
        this.code= code;
    }
}
