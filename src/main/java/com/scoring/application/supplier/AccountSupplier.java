package com.scoring.application.supplier;

import com.scoring.domain.Account;
import com.scoring.domain.AccountType;
import com.scoring.domain.Client;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.*;

@Singleton
public class AccountSupplier {

    public static final long MIN_REQUESTED_AMOUNT = 1000L;
    public static final long MAX_REQUESTED_AMOUNT = 300000L;
    private static final int REQUESTED_AMOUNT_DIVIDER = 1000;
    private static final LocalDate START_INCLUSIVE = LocalDate.of(2000, Month.JANUARY, 1);
    private static final LocalDate END_EXCLUSIVE = LocalDate.of(2022, Month.DECEMBER, 31);

    public Account get(Client client) {
        LocalDate startDate = getRandomDate();
        BigDecimal requestedAmount = randomBigDecimal(BigDecimal.valueOf(MIN_REQUESTED_AMOUNT), BigDecimal.valueOf(MAX_REQUESTED_AMOUNT));
        Integer numberOfInstallments = requestedAmount.intValue() / REQUESTED_AMOUNT_DIVIDER;
        LocalDate endDate = getEndDate(startDate, numberOfInstallments);

        return Account.builder()
                .accountId(UUID.randomUUID())
                .client(client)
                .accountType(AccountType.INSTALLMENT)
                .numberOfInstallments(numberOfInstallments)
                .installmentAmount(BigDecimal.valueOf(REQUESTED_AMOUNT_DIVIDER))
                .initialBalance(requestedAmount)
                .startDate(startDate)
                .endDate(endDate)
                .finished(randomBoolean())
                .build();
    }

    private LocalDate getRandomDate() {
        return randomDate(START_INCLUSIVE, END_EXCLUSIVE);
    }

    private LocalDate getEndDate(LocalDate startDate, Integer installments) {
        return startDate.plus(installments, ChronoUnit.MONTHS);
    }
}
