package com.scoring.domain.range;

import javax.validation.constraints.NotNull;

public record FinishedLoansRange(
        @NotNull Integer firstConditionValue,
        @NotNull Integer secondConditionValue,
        @NotNull Integer lastConditionValue
) {
}
