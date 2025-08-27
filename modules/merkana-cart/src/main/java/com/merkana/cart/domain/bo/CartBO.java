package com.merkana.cart.domain.bo;

import com.merkana.cart.domain.enums.CartStatus;
import com.merkana.core.domain.Auditable;
import com.merkana.core.domain.StoreAware;
import com.merkana.promotion.domain.bo.DiscountCouponBO;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartBO implements StoreAware, Auditable<LocalDateTime>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String storeId;

    private CartStatus status;

    private Set<CartItemBO> items;

    private Set<DiscountCouponBO> coupons;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
