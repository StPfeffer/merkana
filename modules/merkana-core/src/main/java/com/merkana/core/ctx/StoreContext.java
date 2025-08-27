package com.merkana.core.ctx;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public final class StoreContext {

    private static final ThreadLocal<StoreContext> CONTEXT = new ThreadLocal<>();

    private String storeId;

    public static StoreContext getContext() {
        return CONTEXT.get();
    }

    public static void setContext(StoreContext context) {
        CONTEXT.set(context);
    }

    public static void clearContext() {
        StoreContext current = CONTEXT.get();

        if (current != null) {
            log.debug("clearing store context: {}", current.storeId);
        }

        CONTEXT.remove();
    }

}
