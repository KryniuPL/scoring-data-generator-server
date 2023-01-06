package com.scoring.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Account(
        UUID accountId,
        Client client,
        LocalDate startDate,
        BigDecimal initialBalance,
        BigDecimal installmentAmount,
        AccountType accountType,
        Integer numberOfInstallments,
        LocalDate endDate,
        Boolean finished
) {
}
