package com.scoring.infrastructure;

public record DataGenerationRequest(Long numberOfClients, Long numberOfAccountsPerClient) {
}
