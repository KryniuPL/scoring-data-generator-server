package com.scoring.application.producer;

import com.scoring.domain.Account;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.UUID;

@KafkaClient(batch = true)
public interface AccountProducer {

    @Topic("accounts")
    void sendAccount(@KafkaKey UUID accountId, Account account);
}
