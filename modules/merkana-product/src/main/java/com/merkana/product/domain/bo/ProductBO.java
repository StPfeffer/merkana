package com.merkana.product.domain.bo;

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
public class ProductBO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
