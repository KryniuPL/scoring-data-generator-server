package com.scoring.application.supplier;

import com.scoring.domain.account.Account;
import com.scoring.domain.account.AccountStatus;
import com.scoring.domain.client.ClientSummary;
import com.scoring.domain.PaymentHistory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Singleton
public class ClientSummarySupplier {

    @Inject
    private Clock clock;

    public ClientSummary get(List<PaymentHistory> paymentsHistory) {
        List<Account> accounts = paymentsHistory.stream()
                .map(PaymentHistory::account)
                .distinct()
                .toList();

        Integer sumOfBalances = accounts
                .stream()
                .map(Account::initialBalance)
                .reduce(0, Integer::sum);

        Integer maxOverdueAmount = paymentsHistory
                .stream()
                .map(PaymentHistory::overdueAmount)
                .max(Comparator.naturalOrder())
                .orElseThrow();

        Long maxDelayedDays = paymentsHistory
                .stream()
                .map(PaymentHistory::daysOfDelays)
                .max(Comparator.naturalOrder())
                .orElseThrow();

        List<AccountStatus> lastStatuses = paymentsHistory
                .stream()
                .map(PaymentHistory::accountStatus)
                .limit(6)
                .toList();

        AccountStatus worstStatus = paymentsHistory
                .stream()
                .map(PaymentHistory::accountStatus)
                .max(Comparator.comparingInt(AccountStatus::getStatusScore))
                .orElseThrow();

        return ClientSummary.builder()
                .summaryId(UUID.randomUUID())
                .client(paymentsHistory.get(0).client())
                .accounts(accounts)
                .creationDate(LocalDateTime.now(clock))
                .sumOfBalances(sumOfBalances)
                .lastStatuses(lastStatuses)
                .maxOverdueAmount(maxOverdueAmount)
                .maxDelayedDays(maxDelayedDays)
                .worstStatus(worstStatus)
                .build();
    }
}
