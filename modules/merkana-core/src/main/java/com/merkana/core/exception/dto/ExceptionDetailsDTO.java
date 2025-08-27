package com.merkana.core.exception.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Um Objeto de Transferência de Dados (DTO) que representa informações
 * detalhadas sobre uma exceção.
 * <p>
 * Esta classe fornece uma forma estruturada de encapsular e expor detalhes de
 * erro.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetailsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;

    private LocalDateTime timestamp;

    private String errorType;

    private int httpStatus;

    private String httpMethod;

    private String path;

    private List<?> details;

}