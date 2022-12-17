package com.scoring.application.generator;

import com.scoring.application.producer.ClientSummaryProducer;
import com.scoring.application.repository.ClientsRepository;
import com.scoring.application.supplier.ClientSummarySupplier;
import com.scoring.domain.ClientSummary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ClientSummaryGenerator {

    @Inject
    ClientSummaryProducer clientSummaryProducer;

    @Inject
    ClientsRepository clientsRepository;

    @Inject
    ClientSummarySupplier clientSummarySupplier;

    public void generateClientSummaries() {
        clientsRepository.getAllClientsUUIDS()
                .parallelStream()
                .forEach(clientId -> {
                    ClientSummary clientSummary = clientSummarySupplier.get(clientId);
                    clientSummaryProducer.sendClientSummary(clientSummary.summaryId(), clientSummary);
                });
    }
}
