package com.scoring.application.listener;

import com.scoring.application.generator.ScoringGenerator;
import com.scoring.domain.client.ClientSummary;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;
import jakarta.inject.Inject;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ClientSummaryListener {

    @Inject
    ScoringGenerator scoringGenerator;

    @Topic("client-summary")
    public void receiveClientSummary(ClientSummary clientSummary, @MessageHeader("PRODUCER-ID") String producerId) {
        scoringGenerator.generateScoring(clientSummary, producerId);
    }
}
