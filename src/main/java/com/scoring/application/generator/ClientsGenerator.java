package com.scoring.application.generator;

import com.scoring.application.producer.ClientProducer;
import com.scoring.application.supplier.ClientSupplier;
import com.scoring.domain.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Stream;

@Singleton
public class ClientsGenerator {

    @Inject
    private ClientProducer clientProducer;

    @Inject
    private ClientSupplier clientSupplier;

    public List<Client> generateClients(Long numberOfClients) {
        return Stream.generate(clientSupplier).limit(numberOfClients)
                .peek(client -> clientProducer.sendClient(client.clientId(), client))
                .toList();
    }
}
