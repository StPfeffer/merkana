package com.merkana.core.filter.specification;

import com.merkana.core.exception.FilterErrorCode;
import com.merkana.core.exception.MerkanaException;
import com.merkana.core.filter.enums.FilterCondition;
import com.merkana.core.filter.enums.FilterLogic;
import com.merkana.core.filter.request.FilterRequest;
import com.merkana.core.util.DateTimeUtils;
import jakarta.persistence.criteria.*;
import org.hibernate.query.SemanticException;
import org.hibernate.type.descriptor.java.CoercionException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilterSpecification<T> implements Specification<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final FilterRequest filter;

    public FilterSpecification(FilterRequest filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getSubFilters() != null) {
            for (FilterRequest filter : filter.getSubFilters()) {
                predicates.add(buildPredicate(root, cb, filter));
            }
        }

        predicates.add(createPredicate(root, cb, filter));

        if (FilterLogic.AND == filter.getLogic()) {
            return cb.and(predicates.toArray(new Predicate[0]));
        }

        return cb.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate buildPredicate(Path<T> root, CriteriaBuilder cb, FilterRequest filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(createPredicate(root, cb, filter));

        if (filter.getSubFilters() != null) {
            for (FilterRequest subFilter : filter.getSubFilters()) {
                predicates.add(createPredicate(root, cb, subFilter));
            }
        }

        Predicate groupPredicate = FilterLogic.AND == filter.getLogic()
                ? cb.and(predicates.toArray(new Predicate[0]))
                : cb.or(predicates.toArray(new Predicate[0]));

        return FilterLogic.AND == filter.getLogic()
                ? cb.and(groupPredicate)
                : cb.or(groupPredicate);
    }

    @SuppressWarnings("unchecked")
    private Predicate createPredicate(Path<T> root, CriteriaBuilder cb, FilterRequest filter) {
        final String field = filter.getField();
        Path<?> path;

        try {
            path = getPath(root, field);
        } catch (RuntimeException e) {
            throw new MerkanaException(FilterErrorCode.FIELD_NOT_FOUND, e, field, root.getModel().getClass().getSimpleName());
        }

        final Class<?> fieldType = path.getJavaType();

        Object value = filter.getValue();

        if (fieldType.isEnum() && value instanceof String) {
            value = convertEnum((Class<? extends Enum<?>>) fieldType, value, field);
        }

        FilterCondition operator = filter.getCondition();

        Predicate predicate;

        try {
            switch (operator) {
                case EQUALS:
                    predicate = cb.equal(path, value);
                    break;
                case NOT_EQUALS:
                    predicate = cb.notEqual(path, value);
                    break;
                case GREATER_THAN:
                case LESS_THAN:
                case GREATER_THAN_EQUALS:
                case LESS_THAN_EQUALS:
                    predicate = buildComparisonPredicate(cb, path, value, operator);
                    break;
                case LIKE:
                    predicate = cb.like(cb.lower(path.as(String.class)), "%" + String.valueOf(value).toLowerCase() + "%");
                    break;
                case NOT_LIKE:
                    predicate = cb.notLike(cb.lower(path.as(String.class)), "%" + String.valueOf(value).toLowerCase() + "%");
                    break;
                case CONTAINS:
                    if (value instanceof Collection) {
                        if (fieldType.isEnum()) {
                            List<Object> enumValues = new ArrayList<>();
                            for (String enumValue : (Collection<String>) value) {
                                Object convertedValue = convertEnum((Class<? extends Enum<?>>) fieldType, enumValue, field);
                                enumValues.add(convertedValue);
                            }

                            value = enumValues;
                        }

                        predicate = path.in((Collection<?>) value);
                    } else {
                        throw new MerkanaException(FilterErrorCode.CONDITION_REQUIRES_TYPE, "CONTAINS", "Collection");
                    }
                    break;
                case NOT_CONTAINS:
                    if (value instanceof Collection) {
                        predicate = cb.not(path.in((Collection<?>) value));
                    } else {
                        throw new MerkanaException(FilterErrorCode.CONDITION_REQUIRES_TYPE, "NOT_CONTAINS", "Collection");
                    }
                    break;
                case BETWEEN:
                    if (value instanceof Collection) {
                        predicate = handleBetweenOperation(cb, (Collection<?>) value, path);
                        break;
                    } else {
                        throw new MerkanaException(FilterErrorCode.CONDITION_REQUIRES_TYPE, "BETWEEN", "Collection");
                    }
                default:
                    throw new MerkanaException(FilterErrorCode.UNSUPPORTED_FILTER_OPERATION, operator.name());
            }
        } catch (SemanticException | CoercionException e) {
            String expectedType = fieldType.getSimpleName();
            String actualType = value.getClass().getSimpleName();

            throw new MerkanaException(FilterErrorCode.EXPECTED_TYPE_MISMATCH, e, expectedType, actualType, field);
        }

        if (filter.isNegate()) {
            predicate = cb.not(predicate);
        }

        return predicate;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object convertEnum(Class<? extends Enum<?>> fieldType, Object value, String field) {
        try {
            value = Enum.valueOf((Class<? extends Enum>) fieldType, value.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MerkanaException(FilterErrorCode.INVALID_ENUM_NAME, e, value, field, ((Class<? extends Enum>) fieldType).getEnumConstants());
        }

        return value;
    }

    @SuppressWarnings("unchecked")
    private static Predicate handleBetweenOperation(CriteriaBuilder cb, Collection<?> value, Expression<?> path) {
        Object[] values = value.toArray();

        if (values.length != 2) {
            throw new MerkanaException(FilterErrorCode.CONDITION_REQUIRES_X_VALUES, "BETWEEN", 2);
        }

        Object first = values[0];
        Object second = values[1];

        first = convertValue(first, path.getJavaType());
        second = convertValue(second, path.getJavaType());

        if (path.getJavaType().equals(LocalDateTime.class)) {
            return cb.between(
                    path.as(LocalDateTime.class),
                    (LocalDateTime) first,
                    (LocalDateTime) second
            );
        }

        Class<?> clazz = first.getClass();
        Class<?> secondClazz = second.getClass();

        if (Comparable.class.isAssignableFrom(clazz) && Comparable.class.isAssignableFrom(secondClazz)) {
            return cb.between(path.as((Class<? extends Comparable<Object>>) clazz), (Comparable<Object>) first, (Comparable<Object>) second);
        }

        throw new MerkanaException(FilterErrorCode.CONDITION_REQUIRES_TYPE, "BETWEEN", "Comparable");
    }

    private static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) {
            return null;
        }

        if (targetType.isInstance(value)) {
            return value;
        }

        if (value instanceof String stringValue) {
            if (targetType.equals(LocalDateTime.class)) {
                if (DateTimeUtils.isConvertibleToLocalDateTime(stringValue)) {
                    LocalDateTime result = DateTimeUtils.convertToLocalDateTime(stringValue);
                    if (result != null) {
                        return result;
                    }
                    throw new MerkanaException(FilterErrorCode.INVALID_DATE_TIME_FORMAT, stringValue);
                }
                throw new MerkanaException(FilterErrorCode.INVALID_DATE_TIME_FORMAT, stringValue);
            }

            if (targetType.equals(Integer.class)) {
                try {
                    return Integer.parseInt(stringValue);
                } catch (NumberFormatException e) {
                    throw new MerkanaException(FilterErrorCode.INVALID_INTEGER_FORMAT, e, stringValue);
                }
            }

            if (targetType.equals(Long.class)) {
                try {
                    return Long.parseLong(stringValue);
                } catch (NumberFormatException e) {
                    throw new MerkanaException(FilterErrorCode.INVALID_NUMBER_FORMAT, e, stringValue);
                }
            }

            if (targetType.equals(Double.class)) {
                try {
                    return Double.parseDouble(stringValue);
                } catch (NumberFormatException e) {
                    throw new MerkanaException(FilterErrorCode.INVALID_NUMBER_FORMAT, e, stringValue);
                }
            }

            if (targetType.equals(BigDecimal.class)) {
                try {
                    return new BigDecimal(stringValue);
                } catch (NumberFormatException e) {
                    throw new MerkanaException(FilterErrorCode.INVALID_NUMBER_FORMAT, e, stringValue);
                }
            }

            if (targetType.equals(Boolean.class)) {
                return Boolean.parseBoolean(stringValue);
            }

            if (targetType.isEnum()) {
                try {
                    @SuppressWarnings({"unchecked", "rawtypes"})
                    Class<? extends Enum> enumClass = (Class<? extends Enum>) targetType;
                    @SuppressWarnings("unchecked")
                    Enum<?> enumValue = Enum.valueOf(enumClass, stringValue.toUpperCase());

                    return enumValue;
                } catch (IllegalArgumentException e) {
                    throw new MerkanaException(FilterErrorCode.INVALID_STRING_FORMAT, e, stringValue);
                }
            }

            if (targetType.equals(String.class)) {
                return stringValue;
            }

            throw new MerkanaException(FilterErrorCode.UNSUPPORTED_FILTER_TYPE, targetType.getSimpleName());
        }

        if (value instanceof Number number) {
            if (targetType.equals(Double.class)) {
                return number.doubleValue();
            }
            if (targetType.equals(Long.class)) {
                return number.longValue();
            }
            if (targetType.equals(Integer.class)) {
                return number.intValue();
            }
            if (targetType.equals(BigDecimal.class)) {
                return BigDecimal.valueOf(number.doubleValue());
            }
        }

        if (targetType.isInstance(value)) {
            return value;
        }

        throw new MerkanaException(FilterErrorCode.EXPECTED_TYPE_MISMATCH, targetType.getSimpleName(), value.getClass().getSimpleName());
    }

    @SuppressWarnings("unchecked")
    private static <C extends Comparable<C>> Predicate buildComparisonPredicate(CriteriaBuilder cb, Path<?> path,
                                                                                Object value,
                                                                                FilterCondition condition) {

        Class<C> clazz = (Class<C>) path.getJavaType();
        Expression<C> expression = path.as(clazz);

        Object convertedValue = convertValue(value, clazz);
        C typedValue = clazz.cast(convertedValue);

        return switch (condition) {
            case GREATER_THAN -> cb.greaterThan(expression, typedValue);
            case LESS_THAN -> cb.lessThan(expression, typedValue);
            case GREATER_THAN_EQUALS -> cb.greaterThanOrEqualTo(expression, typedValue);
            case LESS_THAN_EQUALS -> cb.lessThanOrEqualTo(expression, typedValue);
            default -> throw new IllegalArgumentException("Unsupported condition: " + condition);
        };
    }

    private static Path<Object> getPath(Path<?> root, String fieldPath) {
        String[] parts = fieldPath.split("\\.");
        Path<?> path = root;

        for (String part : parts) {
            if (path instanceof From<?, ?> from) {
                path = from.get(part);
            } else {
                path = path.get(part);
            }
        }

        @SuppressWarnings("unchecked")
        Path<Object> objectPath = (Path<Object>) path;
        return objectPath;
    }

}
