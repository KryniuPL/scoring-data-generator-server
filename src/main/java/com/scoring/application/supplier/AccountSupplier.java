package com.scoring.application.supplier;

import com.scoring.domain.Account;
import com.scoring.domain.AccountType;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.*;

@Singleton
public class AccountSupplier {

    @Inject
    private Clock clock;

    public Account get(UUID clientId) {
        AccountType accountType = randomEnum(AccountType.class);

        return Account.builder()
                .accountId(UUID.randomUUID())
                .clientId(clientId)
                .accountType(accountType)
                .initialBalance(randomBigDecimal())
                .numberOfInstallments(getNumberOfInstallments(accountType))
                .startDate(LocalDateTime.now(clock))
                .endDate(LocalDateTime.now(clock))
                .vindicationDate(LocalDateTime.now(clock))
                .executionDate(LocalDateTime.now(clock))
                .build();
    }

    private Integer getNumberOfInstallments(AccountType accountType) {
        return switch (accountType) {
            case CREDIT_CARD -> randomInteger(5, 10);
            case MORTGAGE -> randomInteger(12, 240);
            case INSTALLMENT -> randomInteger(1, 10);
            default -> 0;
        };
    }

}
