package com.blueharvest.bank.config.exceptions;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor

public class CreditNotCoveredException extends  RuntimeException {
    private int code;

    public CreditNotCoveredException(int code){
        this.code=code;
    }
    public CreditNotCoveredException(String message,int code){
        super(message);
        this.code=code;
    }
}
