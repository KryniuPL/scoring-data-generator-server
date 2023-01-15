package com.scoring.domain;

import com.scoring.domain.account.Account;
import com.scoring.domain.account.AccountStatus;
import com.scoring.domain.account.AccountType;
import com.scoring.domain.client.Client;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentHistory(
        UUID paymentId,
        Account account,
        Client client,
        AccountType accountType,
        LocalDateTime date,
        Integer balance,
        Long daysOfDelays,
        Integer overdueAmount,
        AccountStatus accountStatus
) {
}
