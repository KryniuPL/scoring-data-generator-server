package com.scoring.application.supplier;

import com.scoring.domain.Account;
import com.scoring.domain.AccountType;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.randomEnum;

@Singleton
public class AccountSupplier {

    @Inject
    private Clock clock;

    public Account get(UUID clientId) {
        return new Account(
                clientId,
                UUID.randomUUID(),
                LocalDateTime.now(clock),
                BigDecimal.ONE,
                randomEnum(AccountType.class),
                0L,
                LocalDateTime.now(clock),
                LocalDateTime.now(clock),
                LocalDateTime.now(clock)
        );
    }
}
