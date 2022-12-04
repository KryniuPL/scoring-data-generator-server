package com.scoring.application.supplier;

import com.scoring.domain.Account;
import com.scoring.domain.PaymentHistory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.randomBigDecimal;

@Singleton
public class PaymentHistorySupplier {

    private static final String PAYMENTS_COUNT_AGGREGATOR = "PAYMENTS_COUNT_AGGREGATOR";
    @Inject
    private Clock clock;

    public PaymentHistory get(Account account) {
        return new PaymentHistory(
                UUID.randomUUID(),
                account.accountId(),
                LocalDateTime.now(clock),
                randomBigDecimal(),
                0L,
                randomBigDecimal(),
                account.accountStatus(),
                PAYMENTS_COUNT_AGGREGATOR
        );
    }
}
