package com.merkana.core.domain;

import java.time.temporal.TemporalAccessor;

public interface Auditable<T extends TemporalAccessor> {

    T getCreatedAt();

    T getUpdatedAt();

}
