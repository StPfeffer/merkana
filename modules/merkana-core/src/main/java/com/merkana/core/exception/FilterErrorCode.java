package com.merkana.core.exception;

public enum FilterErrorCode implements ErrorCode {

    EXPECTED_TYPE_MISMATCH("filter.error.expected_type_mismatch", 400),
    UNSUPPORTED_FILTER_OPERATION("filter.error.unsupported_filter_operation", 400),
    UNSUPPORTED_FILTER_LOGIC("filter.error.unsupported_filter_logic", 400),
    UNSUPPORTED_FILTER_TYPE("filter.error.unsupported_filter_type", 400),
    UNSUPPORTED_AGGREGATION_OPERATION("filter.error.unsupported_aggregation_operation", 400),
    CONDITION_REQUIRES_TYPE("filter.error.condition_requires_type", 400),
    CONDITION_REQUIRES_X_VALUES("filter.error.condition_requires_x_values", 400),
    INVALID_DATE_TIME_FORMAT("filter.error.invalid_date_time_format", 400),
    INVALID_DATE_FORMAT("filter.error.invalid_date_format", 400),
    INVALID_INTEGER_FORMAT("filter.error.invalid_integer_format", 400),
    INVALID_NUMBER_FORMAT("filter.error.invalid_number_format", 400),
    INVALID_STRING_FORMAT("filter.error.invalid_string_format", 400),
    INVALID_ENUM_NAME("filter.error.invalid_enum_name", 400),
    UNSUPPORTED_EXPRESSION_ACESSOR("filter.error.unsupported_expression_accessor", 400),
    FIELD_NOT_FOUND("filter.error.field_not_found", 400);

    private final String messageKey;

    private final int httpStatus;

    FilterErrorCode(String messageKey, int httpStatus) {
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
