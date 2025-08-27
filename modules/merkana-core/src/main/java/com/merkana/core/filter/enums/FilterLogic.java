package com.merkana.core.filter.enums;

import lombok.Getter;

@Getter
public enum FilterLogic implements InternationalizedEnum {

    AND("filter.logic.and"),
    OR("filter.logic.or");

    private final String localizedName;

    FilterLogic(String localizedName) {
        this.localizedName = localizedName;
    }

}
