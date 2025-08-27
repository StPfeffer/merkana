package com.merkana.core.domain.event;

import com.merkana.core.domain.enums.MerkanaModule;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public abstract class MerkanaEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final long timestamp;

    private final long threadId;

    private final String threadName;

    private final MerkanaModule sourceModule;

    private final MerkanaEvent sourceEvent;

    public MerkanaEvent(MerkanaModule sourceModule) {
        this(sourceModule, null);
    }

    public MerkanaEvent(MerkanaModule sourceModule, MerkanaEvent sourceEvent) {
        if (sourceModule == null) {
            throw new IllegalArgumentException("sourceModule must not be null");
        }

        this.timestamp = System.currentTimeMillis();
        this.threadId = Thread.currentThread().threadId();
        this.threadName = Thread.currentThread().getName();
        this.sourceModule = sourceModule;
        this.sourceEvent = sourceEvent;
    }

}
