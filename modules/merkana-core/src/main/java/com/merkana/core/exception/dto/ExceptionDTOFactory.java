package com.merkana.core.exception.dto;

import com.merkana.core.exception.MerkanaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory para criação de instâncias de {@link ExceptionDTO}
 * <p>
 * Esta classe fornece métodos estáticos para criar objetos {@code ExceptionDTO}
 * a partir de diferentes tipos de entrada, como uma mensagem, um timestamp,
 * uma exceção personalizada ou uma exceção genérica. Esses métodos são utilizados
 * para padronizar a representação de exceções na camada de resposta da aplicação.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public final class ExceptionDTOFactory {

    private ExceptionDTOFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static ExceptionDTO create(Exception e, WebRequest request) {
        Throwable cause = e.getCause();

        List<String> details = new ArrayList<>();
        if (cause != null) {
            details.add(cause.getLocalizedMessage());
        }

        String errorType = e.getClass().getSimpleName();

        if (e instanceof MerkanaException merkanaException) {
            errorType = merkanaException.getError().getCode();
        }

        ExceptionDetailsDTO exceptionInfo = ExceptionDetailsDTO.builder()
                .message(e.getLocalizedMessage())
                .errorType(errorType)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .httpMethod(safeGetHttpMethod(request))
                .path(safeGetPath(request))
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();

        if (e instanceof MerkanaException merkanaException) {
            exceptionInfo.setHttpStatus(merkanaException.getError().getHttpStatus());
        }

        return new ExceptionDTO(exceptionInfo);
    }

    private static String safeGetPath(WebRequest request) {
        String path = request != null ? request.getDescription(false) : null;

        if (request != null) {
            path = path.contains("=") ? path.split("=")[1] : path;
        }

        return path;
    }

    private static String safeGetHttpMethod(WebRequest request) {
        if (request instanceof ServletWebRequest servletWebRequest) {
            return servletWebRequest.getRequest().getMethod();
        }

        return null;
    }

}
