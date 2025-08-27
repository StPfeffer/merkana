package com.merkana.core.exception;

/**
 * Enumeração representado códigos de erro padrão usados na aplicação.
 * <p>
 * Esse enum define vários códigos de erros que correspondem a condições comuns
 * de erros encontrados na aplicação.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public enum DefaultErrorCode implements ErrorCode {

    REQUIRED_FIELD("required.field", 400),
    REQUIRED_HEADER("required.header", 400),
    INTERNAL_SERVER_ERROR("internal.server_error", 500),
    REQUIRED_BODY("required.body", 400),
    STRING_CANT_BE_LONGER_THAN("string.cant_be_longer_than", 400),
    STRING_CANT_BE_SHORTER_THAN("string.cant_be_shorter_than", 400),
    NOT_FOUND("not_found", 404),
    ALREADY_EXISTS("already_exists", 400),
    STRING_MUST_BE_BETWEEN("string.must_be_between", 400),
    INVALID_HEADER_VALUE("invalid.header.value", 400),
    INVALID_ENUM_VALUE("invalid.enum.value", 400);

    private final String messageKey;

    private final int httpStatus;

    DefaultErrorCode(String messageKey, int httpStatus) {
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

}
