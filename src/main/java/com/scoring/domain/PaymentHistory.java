package com.scoring.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentHistory(
        UUID accountId,
        LocalDateTime date,
        BigDecimal balance,
        Long daysOfDelays,
        BigDecimal overdueAmount,
        AccountStatus accountStatus
) {
}
