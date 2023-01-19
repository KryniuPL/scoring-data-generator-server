package com.scoring.domain;

import com.scoring.domain.range.ClientsAgeRange;

public record DataGenerationRequest(
        Long numberOfClients,
        Long numberOfAccountsPerClient,
        Long numberOfPaymentsPerAccount,
        ClientsAgeRange clientsAgeRange
) {

    public Long getNumberOfPaymentsPerClient() {
        return this.numberOfAccountsPerClient * this.numberOfPaymentsPerAccount;
    }
}
