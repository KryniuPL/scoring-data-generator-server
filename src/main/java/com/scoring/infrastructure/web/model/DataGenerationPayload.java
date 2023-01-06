package com.scoring.infrastructure.web.model;

import com.scoring.domain.DataGenerationRequest;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.scoring.application.utils.RandomUtils.randomLong;

@Introspected
public record DataGenerationPayload(
        @NotNull @Min(value = 1, message = "Number of clients must be greater than 0") Long numberOfClients,
        @NotNull @Min(value = 1, message = "Number of accounts must be greater than 0") AccountsNumberRange accountsNumberRange,
        @NotNull @Min(value = 1, message = "Number of payments must be greater than 0") Long numberOfPaymentsPerAccount
) {

    public DataGenerationRequest toDataGenerationRequest() {
        Long accountsNumber = accountsNumberRange.explicit == null ?
                randomLong(accountsNumberRange.min, accountsNumberRange.max) : accountsNumberRange.explicit;

        return new DataGenerationRequest(
                numberOfClients,
                accountsNumber,
                numberOfPaymentsPerAccount
        );
    }

    record AccountsNumberRange(
            @NotNull @Min(value = 1, message = "Number of accounts must be greater than 0") Long min,
            @NotNull @Max(value = 5, message = "Max number of accounts is 5") Long max,
            @Max(value = 5, message = "Max number of clients is 5") Long explicit
    ) {
    }

}
