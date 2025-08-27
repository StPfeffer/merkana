package com.merkana.promotion.domain.bo;

import com.merkana.core.domain.Auditable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCouponBO implements Auditable<LocalDateTime>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String code;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
