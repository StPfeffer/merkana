package com.merkana.core.exception.dto;

import java.io.Serializable;

/**
 * Um Objeto de Transferência de Dados (DTO) para encapsular detalhes de exceções
 * em um formato estruturado.
 * <p>
 * Este DTO é destinado a ser utilizado para apresentar informações de erro aos
 * clientes de forma consistente.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public record ExceptionDTO(ExceptionDetailsDTO error) implements Serializable {

}