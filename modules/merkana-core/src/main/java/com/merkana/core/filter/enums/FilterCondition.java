package com.merkana.core.filter.enums;

import lombok.Getter;

import java.util.Collection;

@Getter
public enum FilterCondition implements InternationalizedEnum {

    EQUALS("filter.condition.equals", new Class[]{Object.class}),
    NOT_EQUALS("filter.condition.not_equals", new Class[]{Object.class}),
    GREATER_THAN("filter.condition.greater", new Class[]{Number.class, String.class}),
    LESS_THAN("filter.condition.less", new Class[]{Number.class, String.class}),
    GREATER_THAN_EQUALS("filter.condition.greater_equals", new Class[]{Number.class, String.class}),
    LESS_THAN_EQUALS("filter.condition.less_equals", new Class[]{Number.class, String.class}),
    LIKE("filter.condition.like", new Class[]{Number.class, CharSequence.class}),
    NOT_LIKE("filter.condition.not_like", new Class[]{Object.class}),
    CONTAINS("filter.condition.contains", new Class[]{Collection.class}),
    NOT_CONTAINS("filter.condition.not_contains", new Class[]{Collection.class}),
    BETWEEN("filter.condition.between", new Class[]{Collection.class});

    private final String localizedName;

    private final Class<?>[] requiredClasses;

    FilterCondition(String localizedName, Class<?>[] requiredClasses) {
        this.localizedName = localizedName;
        this.requiredClasses = requiredClasses;
    }

}
