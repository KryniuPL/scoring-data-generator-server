package com.scoring.domain.range;

import javax.validation.constraints.NotNull;

public record CustomerLoadRange(
        @NotNull Double firstConditionValue,
        @NotNull Double secondConditionValue,
        @NotNull Double thirdConditionValue,
        @NotNull Double lastConditionValue
) {
}
