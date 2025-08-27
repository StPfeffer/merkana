package com.merkana.core.filter.request;

import com.merkana.core.filter.enums.FilterCondition;
import com.merkana.core.filter.enums.FilterLogic;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {

    @NotBlank
    private String field;

    @NotNull
    private Object value;

    @Builder.Default
    @NotNull
    private FilterCondition condition = FilterCondition.EQUALS;

    @Builder.Default
    @NotNull
    private FilterLogic logic = FilterLogic.AND;

    @Builder.Default
    @NotNull
    private boolean negate = false;

    @Valid
    private List<FilterRequest> subFilters;

}
