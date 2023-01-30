package com.scoring.domain.range;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

@Introspected
public record ScoringCardConfig(
        @NotNull CustomerLoadRange customerLoadRange,
        @NotNull SeniorityRange customerSeniorityRange,
        @NotNull ActualLoansRange actualLoansRange,
        @NotNull FinishedLoansRange finishedLoansRange,
        @NotNull ChildrenRange childrenRange
) {
}

