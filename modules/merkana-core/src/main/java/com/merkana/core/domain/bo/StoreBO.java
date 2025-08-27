package com.merkana.core.domain.bo;

import com.merkana.core.domain.Auditable;
import com.merkana.core.domain.enums.StoreStatus;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreBO implements Auditable<LocalDateTime>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private StoreStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
