package com.scoring.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.randomDate;

@Data
@Builder
public final class Account {
    private final UUID clientId;
    private final UUID accountId;
    private final LocalDate startDate;
    private final BigDecimal initialBalance;
    private final AccountType accountType;
    private final Integer numberOfInstallments;
    private final LocalDate endDate;
    private LocalDate vindicationDate;
    private LocalDate executionDate;

    public Account withVindicationDate() {
        this.vindicationDate = randomDate(this.startDate, this.endDate);
        return this;
    }

    public Account withExecutionDate() {
        this.executionDate = randomDate(this.startDate, this.endDate);
        return this;
    }

}
