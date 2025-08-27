package com.merkana.core.exception;

public enum OperationErrorCode implements ErrorCode {

    NOT_ALLOWED("operation.not_allowed", 403);

    private final String code;

    private final String messageKey;

    private final int httpStatus;

    OperationErrorCode(String messageKey, int httpStatus) {
        this.code = this.name();
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return code;
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
