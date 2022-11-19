package com.scoring.infrastructure.web.model;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Introspected
public record PaymentsRange(
        @NotNull
        @Min(value = 1, message = "Minimum range must be greater than 0")
        Integer min,
        @NotNull
        @Min(value = 1, message = "Minimum range must be greater than 0")
        @Max(value = 100, message = "Maximum range is 100")
        Integer max,
        Integer fixedValue
) {
}
