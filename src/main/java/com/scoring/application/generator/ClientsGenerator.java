package com.scoring.application.generator;

import com.scoring.application.producer.ClientProducer;
import com.scoring.application.supplier.ClientSupplier;
import com.scoring.domain.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ClientsGenerator {

    @Inject
    private ClientProducer clientProducer;

    @Inject
    private ClientSupplier clientSupplier;

    public void generateClients(Long numberOfClients) {
        for (int i = 0; i < numberOfClients; i++) {
            Client client = clientSupplier.get();
            clientProducer.sendClient(client.clientId(), client);
        }
    }
}
