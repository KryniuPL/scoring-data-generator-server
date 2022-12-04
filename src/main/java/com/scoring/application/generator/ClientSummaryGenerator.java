package com.scoring.application.generator;

import com.scoring.application.producer.ClientSummaryProducer;
import com.scoring.application.repository.AccountsRepository;
import com.scoring.application.repository.ClientsRepository;
import com.scoring.domain.Account;
import com.scoring.domain.ClientSummary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Singleton
public class ClientSummaryGenerator {

    @Inject
    ClientSummaryProducer clientSummaryProducer;

    @Inject
    ClientsRepository clientsRepository;

    @Inject
    AccountsRepository accountsRepository;

    @Inject
    private Clock clock;

    public void generateClientSummaries() {
        clientsRepository.getAllClientsUUIDS()
                .forEach(clientId -> {
                    List<Account> clientAccounts = accountsRepository.getAllByClientId(clientId);

                    ClientSummary clientSummary = ClientSummary.builder()
                            .summaryId(UUID.randomUUID())
                            .clientId(clientId)
                            .creationDate(LocalDateTime.now(clock))
                            .accountTypes(clientAccounts.stream().map(Account::accountType).toList())
                            .build();

                    clientSummaryProducer.sendClientSummary(clientSummary.summaryId(), clientSummary);
                });
    }
}
