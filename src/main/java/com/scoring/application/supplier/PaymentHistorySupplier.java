package com.scoring.application.supplier;

import com.scoring.domain.Account;
import com.scoring.domain.AccountStatus;
import com.scoring.domain.PaymentHistory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.*;

@Singleton
public class PaymentHistorySupplier {

    private static final String PAYMENTS_COUNT_AGGREGATOR = "PAYMENTS_COUNT_AGGREGATOR";

    public PaymentHistory get(Account account) {
        boolean isDelayed = randomBoolean();
        Long daysOfDelays = isDelayed ? randomLong(1, 30) : 0L;
        BigDecimal overdueAmount = isDelayed ? randomBigDecimal() : BigDecimal.ZERO;

        return PaymentHistory.builder()
                .paymentId(UUID.randomUUID())
                .accountId(account.getAccountId())
                .accountStatus(randomEnum(AccountStatus.class))
                .balance(randomBigDecimal())
                .date(randomDate(account.getStartDate(), account.getEndDate()).atTime(LocalTime.MIDNIGHT))
                .daysOfDelays(daysOfDelays)
                .overdueAmount(overdueAmount)
                .paymentsCountAggregator(PAYMENTS_COUNT_AGGREGATOR)
                .build();
    }
}
