package com.scoring.application.producer;

import com.scoring.domain.Account;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;

import java.util.UUID;

@KafkaClient
public interface AccountProducer {

    @Topic("accounts")
    void sendAccount(@KafkaKey UUID accountId, Account account, @MessageHeader("PRODUCER-ID") String producerId);
}
