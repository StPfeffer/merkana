package com.merkana.core.exception;

import com.merkana.core.util.ArrayUtils;
import com.merkana.core.util.MessageResolver;
import com.merkana.core.util.StringUtils;
import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serial;
import java.util.Locale;

@Getter
public class MerkanaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;

    private final ErrorCode error;

    private final String messageKey;

    private final Locale locale;

    private final Object[] args;

    public MerkanaException(String message) {
        this.message = message;
        this.error = DefaultErrorCode.INTERNAL_SERVER_ERROR;
        this.messageKey = null;
        this.locale = LocaleContextHolder.getLocale();
        this.args = null;
    }

    public MerkanaException(ErrorCode errorCode, Object... args) {
        this(errorCode, LocaleContextHolder.getLocale(), args);
    }

    public MerkanaException(ErrorCode errorCode, Throwable cause, Object... args) {
        this(errorCode, LocaleContextHolder.getLocale(), cause, args);
    }

    public MerkanaException(ErrorCode errorCode, Locale locale, Object... args) {
        this.message = null;
        this.error = errorCode;
        this.messageKey = errorCode.getMessageKey();
        this.locale = locale;
        this.args = args;
    }

    public MerkanaException(ErrorCode errorCode, Locale locale, Throwable cause, Object... args) {
        super(cause);
        this.message = null;
        this.error = errorCode;
        this.messageKey = errorCode.getMessageKey();
        this.locale = locale;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return getLocalizedMessage();
    }

    @Override
    public String getLocalizedMessage() {
        if (!StringUtils.isEmpty(message)) {
            return message;
        }

        String localizedMessage = MessageResolver.resolveMessage(messageKey, locale);

        boolean hasOperationPlaceholder = localizedMessage.contains("{operation}");
        boolean hasEntityPlaceholder = localizedMessage.contains("{entity}");

        if (hasOperationPlaceholder && hasEntityPlaceholder) {
            if (!ArrayUtils.isEmpty(args) && args.length > 0) {
                String operationKey = args[0].toString();
                String operationName = MessageResolver.resolveOperation(operationKey, locale);
                localizedMessage = localizedMessage.replace("{operation}", operationName);
            }

            if (!ArrayUtils.isEmpty(args) && args.length > 1) {
                String entityKey = args[1].toString();
                String entityName = MessageResolver.resolveEntity(entityKey, locale);
                localizedMessage = localizedMessage.replace("{entity}", entityName);
            }
        } else if (hasOperationPlaceholder) {
            if (!ArrayUtils.isEmpty(args) && args.length > 0) {
                String operationKey = args[0].toString();
                String operationName = MessageResolver.resolveOperation(operationKey, locale);
                localizedMessage = localizedMessage.replace("{operation}", operationName);
            }
        } else if (hasEntityPlaceholder) {
            if (!ArrayUtils.isEmpty(args) && args.length > 0) {
                String entityKey = args[0].toString();
                String entityName = MessageResolver.resolveEntity(entityKey, locale);
                localizedMessage = localizedMessage.replace("{entity}", entityName);
            }
        }

        return StringUtils.getMessageFormat(localizedMessage, args);
    }

}
