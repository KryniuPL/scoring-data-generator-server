package com.scoring.application.producer;

import com.scoring.domain.PaymentHistory;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.UUID;

@KafkaClient
public interface PaymentHistoryProducer {
    @Topic("payments-history")
    void sendPaymentHistory(@KafkaKey UUID paymentId, PaymentHistory paymentHistory);
}
