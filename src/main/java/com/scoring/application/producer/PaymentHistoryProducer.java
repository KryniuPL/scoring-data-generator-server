package com.scoring.application.producer;

import com.scoring.domain.PaymentHistory;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;

import java.util.UUID;

@KafkaClient
public interface PaymentHistoryProducer {
    @Topic("payments-history")
    void sendPaymentHistory(@KafkaKey UUID paymentId, PaymentHistory paymentHistory, @MessageHeader("PRODUCER-ID") String producerId);
}
