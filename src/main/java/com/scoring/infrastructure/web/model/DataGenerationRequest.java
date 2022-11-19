package com.scoring.infrastructure.web.model;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Introspected
public record DataGenerationRequest(
        @NotNull @Min(value = 1, message = "Number of clients must be greater than 0") Long numberOfClients,
        @NotNull @Min(value = 1, message = "Number of accounts must be greater than 0") Long numberOfAccountsPerClient,
        @NotNull PaymentsRange paymentsRange
) {

}
