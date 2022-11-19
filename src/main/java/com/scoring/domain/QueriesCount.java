package com.scoring.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record QueriesCount(
        UUID clientId,
        AccountType accountType,
        BigDecimal initialBalance,
        LocalDateTime queryDate
) {
}
