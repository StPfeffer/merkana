package com.merkana.core.exception;

public enum AuthErrorCode implements ErrorCode {

    BAD_CREDENTIALS("auth.error.bad_credentials", 401),
    DISABLED("auth.error.disabled", 401),
    CREDENTIALS_EXPIRED("auth.error.credentials_expired", 401),
    UNAUTHORIZED("auth.error.unauthorized", 401),
    USER_NOT_FOUND("auth.error.user_not_found", 404),
    EMAIL_ALREADY_IN_USE("auth.error.email_already_in_use", 400),
    EMAIL_NOT_FOUND("auth.error.email_not_found", 404),
    USERNAME_ALREADY_IN_USE("auth.error.username_already_in_use", 400),
    USERNAME_NOT_FOUND("auth.error.username_not_found", 404),
    INVALID_ROLE("auth.error.invalid_role", 400),
    INVALID_ROLE_NAME("auth.error.invalid_role_name", 400),
    INVALID_ROLE_DESCRIPTION("auth.error.invalid_role_description", 400),
    INVALID_ROLE_PERMISSION("auth.error.invalid_role_permission", 400),
    SITE_NOT_ALLOWED("auth.error.site_not_allowed", 403),
    SOMETHING_WRONG("auth.error.something_wrong", 500),
    ACCOUNT_LOCKED("auth.error.account.locked", 401),
    ACCOUNT_EXPIRED("auth.error.account.expired", 403),
    TOKEN_EXPIRED("auth.token.expired", 401),
    INVALID_TOKEN("auth.token.invalid", 401),
    INVALID_REFRESH_TOKEN("auth.refresh.token.invalid", 401),
    INVALID_ACTION("auth.action.invalid", 400);

    private final String messageKey;

    private final int httpStatus;

    AuthErrorCode(String messageKey, int httpStatus) {
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
