package com.scoring.application.listener;

import com.scoring.application.generator.AccountsGenerator;
import com.scoring.domain.Client;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;
import jakarta.inject.Inject;

import java.util.UUID;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ClientsListener {

    @Inject
    private AccountsGenerator accountsGenerator;

    @Topic("clients")
    public void receiveClient(Client client, @MessageHeader("PRODUCER-ID") String producerId) {
        accountsGenerator.generateAccount(client, producerId);
    }
}
