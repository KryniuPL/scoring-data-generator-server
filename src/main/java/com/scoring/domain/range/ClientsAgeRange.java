package com.scoring.domain.range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record ClientsAgeRange (
        @NotNull @Min(value = 18, message = "Clients minimum age must be greater or equal 18") Integer min,
        @NotNull @Max(value = 100, message = "Clients maximum age must be greater or equal 100") Integer max
) {
}
