package com.scoring.application.producer;

import com.scoring.domain.scoring.Scoring;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;

import java.util.UUID;

@KafkaClient
public interface ScoringProducer {

    @Topic("scoring")
    void sendScoring(@KafkaKey UUID scoringId, Scoring scoring, @MessageHeader("PRODUCER-ID") String producerId);
}
