package com.scoring.application.producer;

import com.scoring.domain.ClientSummary;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;

import java.util.UUID;

@KafkaClient
public interface ClientSummaryProducer {

    @Topic("client-summary")
    void sendClientSummary(@KafkaKey UUID summaryId, ClientSummary clientSummary, @MessageHeader("PRODUCER-ID") String producerId);
}
