package com.merkana.core.filter.specification;

import com.merkana.core.filter.request.FilterRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public final class SpecificationBuilder {

    private SpecificationBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> Specification<T> build(FilterRequest filterRequest) {
        return new FilterSpecification<>(filterRequest);
    }

    public static <T> Specification<T> build(List<FilterRequest> filterRequest) {
        return new FilterListSpecification<>(filterRequest);
    }

}
