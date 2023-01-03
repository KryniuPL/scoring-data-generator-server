package com.scoring.application.supplier;

import com.scoring.domain.Account;
import com.scoring.domain.AccountType;
import com.scoring.domain.Client;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.*;

@Singleton
public class AccountSupplier {

    private static final LocalDate START_INCLUSIVE = LocalDate.of(2000, Month.JANUARY, 1);
    private static final LocalDate END_EXCLUSIVE = LocalDate.of(2021, Month.DECEMBER, 31);

    public Account get(Client client) {
        AccountType accountType = randomEnum(AccountType.class);
        LocalDate startDate = getRandomDate();
        LocalDate endDate = getEndDate(startDate);

        return Account.builder()
                .accountId(UUID.randomUUID())
                .clientId(client.clientId())
                .clientJob(client.clientJob())
                .clientMartialStatus(client.clientMartialStatus())
                .accountType(accountType)
                .initialBalance(randomBigDecimal())
                .numberOfInstallments(getNumberOfInstallments(accountType))
                .startDate(startDate)
                .endDate(endDate)
                .vindicationDate(null)
                .executionDate(null)
                .build();
    }

    private LocalDate getRandomDate() {
        return randomDate(START_INCLUSIVE, END_EXCLUSIVE);
    }

    private LocalDate getEndDate(LocalDate startDate) {
        return startDate.plus(13, ChronoUnit.MONTHS);
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
