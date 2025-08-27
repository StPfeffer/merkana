package com.merkana.core.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String code;

    private String fractionDigits;

    private String name;

    private String symbol;

}
