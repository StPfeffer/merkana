package com.merkana.core.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Money implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String currencyCode;

    private String currencySymbol;

    private BigDecimal amount;

    private int fractionDigits;

    private int integerAmount;

}
