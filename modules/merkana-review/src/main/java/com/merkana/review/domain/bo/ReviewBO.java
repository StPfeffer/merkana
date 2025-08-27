package com.merkana.review.domain.bo;

import com.merkana.core.domain.Auditable;
import com.merkana.core.domain.StoreAware;
import com.merkana.review.domain.enums.ReviewStatus;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewBO implements StoreAware, Auditable<LocalDateTime>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String storeId;

    private String productId;

    private String customerId;

    private ReviewStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
