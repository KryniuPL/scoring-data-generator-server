package com.scoring.domain.range;

import javax.validation.constraints.NotNull;

public record SeniorityRange(
        @NotNull Integer firstConditionValue,
        @NotNull Integer secondConditionValue,
        @NotNull Integer lastConditionValue
) {
}
