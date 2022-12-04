package com.scoring.infrastructure.repository;

import com.scoring.application.repository.AccountsRepository;
import com.scoring.domain.Account;
import com.scoring.domain.AccountType;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.Row;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class KafkaAccountsRepository implements AccountsRepository {

    @Inject
    private Client kafkaDatabaseClient;

    @Override
    public List<Account> getAllByClientId(UUID clientId) {
        return kafkaDatabaseClient.executeQuery("select * from accounts where CLIENTID = '%s';".formatted(clientId))
                .join()
                .stream()
                .map(this::mapRow)
                .toList();
    }

    private Account mapRow(Row row) {
        return Account.builder()
                .accountId(UUID.fromString(row.getString("ACCOUNTID")))
                .accountType(AccountType.valueOf(row.getString("ACCOUNTTYPE")))
                .build();
    }
}
