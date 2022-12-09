package com.scoring.infrastructure.repository;

import com.scoring.application.repository.PaymentsRepository;
import com.scoring.domain.AccountStatus;
import com.scoring.domain.PaymentHistory;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.Row;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Singleton
public class KafkaPaymentsRepository implements PaymentsRepository {

    @Inject
    private Client kafkaDatabaseClient;

    @Override
    public Long countAll() {
        return kafkaDatabaseClient.executeQuery("select * from payments_metrics;")
                .join()
                .get(0)
                .getLong("PAYMENTS_COUNT");
    }

    @Override
    public List<PaymentHistory> getAllByAccountId(UUID accountId) {
        return kafkaDatabaseClient.executeQuery("select * from payments where ACCOUNTID = '%s';".formatted(accountId))
                .join()
                .stream()
                .map(this::mapRow)
                .toList();
    }

    private PaymentHistory mapRow(Row row) {
        return PaymentHistory.builder()
                .accountStatus(AccountStatus.valueOf(row.getString("ACCOUNTSTATUS")))
                .balance(new BigDecimal(row.getString("BALANCE")))
                .daysOfDelays(Long.valueOf(row.getString("DAYSOFDELAYS")))
                .overdueAmount(new BigDecimal(row.getString("OVERDUEAMOUNT")))
                .build();
    }


}
