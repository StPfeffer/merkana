package com.merkana.cart.domain.event;

import com.merkana.core.domain.enums.MerkanaModule;
import com.merkana.core.domain.event.MerkanaEvent;

public class CartCreatedEvent extends MerkanaEvent {

    public CartCreatedEvent() {
        super(MerkanaModule.CART);
    }

}
