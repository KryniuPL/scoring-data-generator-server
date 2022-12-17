package com.scoring.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ClientSummary(
        UUID summaryId,
        UUID clientId,
        List<AccountType> accountTypes,
        BigDecimal sumOfBalances,
        AccountStatus worstStatus,
        Long maxDelayedDays,
        LocalDateTime creationDate,
        BigDecimal maxOverdueAmount,
        List<AccountStatus> lastStatuses
) {
}
