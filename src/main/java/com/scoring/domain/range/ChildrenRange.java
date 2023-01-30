package com.scoring.domain.range;

import javax.validation.constraints.NotNull;

public record ChildrenRange(
        @NotNull Integer min,
        @NotNull Integer max
) {
}
