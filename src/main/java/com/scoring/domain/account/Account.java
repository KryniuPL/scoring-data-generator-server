package com.scoring.domain.account;

import com.scoring.domain.client.Client;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Account(
        UUID accountId,
        Client client,
        LocalDate startDate,
        Integer initialBalance,
        Integer installmentAmount,
        AccountType accountType,
        Integer numberOfInstallments,
        LocalDate endDate,
        Boolean finished
) {
}
