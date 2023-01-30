package com.scoring.infrastructure.web.model;

import com.scoring.domain.DataGenerationRequest;
import com.scoring.domain.range.ChildrenRange;
import com.scoring.domain.range.ClientsAgeRange;
import com.scoring.domain.range.ScoringCardConfig;
import io.micronaut.core.annotation.Introspected;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.scoring.application.utils.RandomUtils.randomLong;

@Introspected
public record DataGenerationPayload(
        @NotNull @Valid AccountsNumberRange accountsRange,
        @NotNull @Valid PaymentsNumberRange paymentsRange,
        @NotNull @Valid ClientsAgeRange clientsAgeRange,
        @NotNull @Valid ChildrenRange childrenRange,
        @NotNull @Valid ScoringCardConfig scoringCardConfig
) {

    public DataGenerationRequest toDataGenerationRequest() {
        return new DataGenerationRequest(
                10000L,
                randomLong(accountsRange.min, accountsRange.max),
                randomLong(paymentsRange.min, paymentsRange.max),
                clientsAgeRange,
                childrenRange,
                scoringCardConfig
        );
    }

    record AccountsNumberRange(
            @NotNull @Min(value = 1, message = "Number of accounts must be greater than 0") Long min,
            @NotNull @Max(value = 5, message = "Max number of accounts is 5") Long max
    ) {
    }

    record PaymentsNumberRange(
            @NotNull @Min(value = 1, message = "Number of accounts must be greater than 0") Long min,
            @NotNull @Max(value = 100, message = "Max number of accounts is 100") Long max
    ) {
    }
}
