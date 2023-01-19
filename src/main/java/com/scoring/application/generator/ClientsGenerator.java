package com.scoring.application.generator;

import com.scoring.application.producer.ClientProducer;
import com.scoring.application.supplier.ClientSupplier;
import com.scoring.application.utils.GenerationDataHolder;
import com.scoring.domain.client.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;

import java.util.UUID;

@Singleton
public class ClientsGenerator {

    @Inject
    private ClientProducer clientProducer;

    @Inject
    private ClientSupplier clientSupplier;

    @SneakyThrows
    public String generateClients(String producerId) {
        Long numberOfClients = GenerationDataHolder.getCurrentGenerationData().numberOfClients();
        String threadName = UUID.randomUUID().toString();

        new Thread(() -> {
            for (int i = 0; i < numberOfClients; i++) {
                Client client = clientSupplier.get(producerId);
                clientProducer.sendClient(client.clientId(), client, producerId);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, threadName).start();

        return threadName;
    }
}
