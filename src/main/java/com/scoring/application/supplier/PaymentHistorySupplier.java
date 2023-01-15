package com.scoring.application.supplier;

import com.scoring.domain.account.Account;
import com.scoring.domain.account.AccountStatus;
import com.scoring.domain.PaymentHistory;
import jakarta.inject.Singleton;

import java.time.LocalTime;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.*;

@Singleton
public class PaymentHistorySupplier {

    public PaymentHistory get(Account account) {
        boolean isDelayed = randomBoolean();
        Long daysOfDelays = isDelayed ? randomLong(1, 30) : 0L;
        Integer overdueAmount = isDelayed ? randomInteger(100, 1000) : 0;

        return PaymentHistory.builder()
                .paymentId(UUID.randomUUID())
                .account(account)
                .client(account.client())
                .accountType(account.accountType())
                .accountStatus(randomEnum(AccountStatus.class))
                .balance(1000)
                .date(randomDate(account.startDate(), account.endDate()).atTime(LocalTime.MIDNIGHT))
                .daysOfDelays(daysOfDelays)
                .overdueAmount(overdueAmount)
                .build();
    }
}
