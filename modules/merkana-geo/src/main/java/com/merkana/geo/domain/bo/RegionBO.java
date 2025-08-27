package com.merkana.geo.domain.bo;

import com.merkana.core.domain.Currency;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionBO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String key;

    private String name;

    private String description;

    private Currency currency;

    private String slug;

    private String storeId;

    private boolean isDefault;

    private boolean defaultIsRefundable;

    private boolean defaultIsReturnable;

    private boolean defaultIsSwapabble;

    private int defaultRefundableWindow;

    private int defaultReturnableWindow;

    private int defaultSwapableWindow;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
