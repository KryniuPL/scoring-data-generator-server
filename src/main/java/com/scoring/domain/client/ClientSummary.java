package com.scoring.domain.client;

import com.scoring.domain.account.Account;
import com.scoring.domain.account.AccountStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ClientSummary(
        UUID summaryId,
        Client client,
        List<Account> accounts,
        Integer sumOfBalances,
        AccountStatus worstStatus,
        Long maxDelayedDays,
        LocalDateTime creationDate,
        Integer maxOverdueAmount,
        List<AccountStatus> lastStatuses
) {
}
