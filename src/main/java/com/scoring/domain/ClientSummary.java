package com.scoring.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ClientSummary(
        UUID clientId,
        AccountType accountType,
        BigDecimal sumOfBalances,
        AccountStatus worstStatus,
        Long maxDelayedDays,
        LocalDateTime creationDate,
        BigDecimal maxOverdueAmount,
        List<AccountStatus> lastStatuses
) {
}
