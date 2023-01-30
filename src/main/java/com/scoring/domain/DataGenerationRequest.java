package com.scoring.domain;

import com.scoring.domain.range.ChildrenRange;
import com.scoring.domain.range.ClientsAgeRange;
import com.scoring.domain.range.ScoringCardConfig;

public record DataGenerationRequest(
        Long numberOfClients,
        Long numberOfAccountsPerClient,
        Long numberOfPaymentsPerAccount,
        ClientsAgeRange clientsAgeRange,
        ChildrenRange childrenRange,
        ScoringCardConfig scoringCardConfig
) {
    public DataGenerationRequest withScoringCardConfig(ScoringCardConfig scoringCardConfig) {
        return new DataGenerationRequest(
                this.numberOfClients,
                this.numberOfAccountsPerClient,
                this.numberOfPaymentsPerAccount,
                this.clientsAgeRange,
                this.childrenRange,
                scoringCardConfig
        );
    }

    public Long getNumberOfPaymentsPerClient() {
        return this.numberOfAccountsPerClient * this.numberOfPaymentsPerAccount;
    }
}
