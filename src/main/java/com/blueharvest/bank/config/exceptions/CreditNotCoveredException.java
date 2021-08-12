package com.blueharvest.bank.config.exceptions;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class CreditNotCoveredException extends  RuntimeException {
    private int code;

}
