package com.scoring.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Account(
        UUID clientId,
        UUID accountId,
        ClientJob clientJob,
        ClientMartialStatus clientMartialStatus,
        LocalDate startDate,
        BigDecimal initialBalance,
        AccountType accountType,
        Integer numberOfInstallments,
        LocalDate endDate,
        LocalDate vindicationDate,
        LocalDate executionDate
) {
}
