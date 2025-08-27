package com.merkana.core.exception.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String field;

    private String message;

}
