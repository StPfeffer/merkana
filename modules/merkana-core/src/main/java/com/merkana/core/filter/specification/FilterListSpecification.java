package com.merkana.core.filter.specification;

import com.merkana.core.filter.request.FilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class FilterListSpecification<T> implements Specification<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<? extends FilterRequest> filters;

    public FilterListSpecification(List<? extends FilterRequest> filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        for (FilterRequest filter : filters) {
            Specification<T> filterSpecification = new FilterSpecification<>(filter);

            predicates.add(filterSpecification.toPredicate(root, query, criteriaBuilder));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
