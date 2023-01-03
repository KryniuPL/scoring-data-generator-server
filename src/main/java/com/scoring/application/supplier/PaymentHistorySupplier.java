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

    public PaymentHistory get(Account account) {
        boolean isDelayed = randomBoolean();
        Long daysOfDelays = isDelayed ? randomLong(1, 30) : 0L;
        BigDecimal overdueAmount = isDelayed ? randomBigDecimal() : BigDecimal.ZERO;

        return PaymentHistory.builder()
                .paymentId(UUID.randomUUID())
                .accountId(account.accountId())
                .clientId(account.clientId())
                .accountType(account.accountType())
                .accountStatus(randomEnum(AccountStatus.class))
                .balance(randomBigDecimal())
                .clientJob(account.clientJob())
                .clientMartialStatus(account.clientMartialStatus())
                .date(randomDate(account.startDate(), account.endDate()).atTime(LocalTime.MIDNIGHT))
                .daysOfDelays(daysOfDelays)
                .overdueAmount(overdueAmount)
                .build();
    }
}
