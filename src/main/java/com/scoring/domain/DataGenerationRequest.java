package com.scoring.domain;

public record DataGenerationRequest(
        Long numberOfClients,
        Long numberOfAccountsPerClient,
        Long numberOfPaymentsPerAccount
) {

    public Long getNumberOfPaymentsPerClient() {
        return this.numberOfAccountsPerClient * this.numberOfPaymentsPerAccount;
    }
}
