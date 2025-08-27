package com.merkana.order.domain.bo;

import com.merkana.core.domain.Auditable;
import com.merkana.core.domain.StoreAware;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBO implements StoreAware, Auditable<LocalDateTime>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String orderNumber;

    private String storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
