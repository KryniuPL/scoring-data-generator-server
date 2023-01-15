package com.scoring.application.producer;

import com.scoring.domain.client.Client;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;

import java.util.UUID;

@KafkaClient
public interface ClientProducer {

    @Topic("clients")
    void sendClient(@KafkaKey UUID clientId, Client client, @MessageHeader("PRODUCER-ID") String producerId);
}
