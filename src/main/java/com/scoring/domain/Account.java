package com.scoring.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Account(
        UUID clientId,
        UUID accountId,
        LocalDateTime startDate,
        BigDecimal initialBalance,
        AccountType accountType,
        AccountStatus accountStatus,
        Long numberOfInstallments,
        LocalDateTime endDate,
        LocalDateTime vindicationDate,
        LocalDateTime executionDate
) {
}
