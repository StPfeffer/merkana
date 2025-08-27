package com.merkana.cart.domain.bo;

import com.merkana.cart.domain.enums.CartItemStatus;
import com.merkana.core.domain.Auditable;
import com.merkana.core.domain.Money;
import com.merkana.product.domain.bo.ProductBO;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemBO implements Auditable<LocalDateTime>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private ProductBO product;

    private BigDecimal quantity;

    private Money originalPrice;

    private Money currentPrice;

    private CartItemStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
