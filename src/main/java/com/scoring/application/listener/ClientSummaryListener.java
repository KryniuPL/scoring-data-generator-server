package com.scoring.application.listener;

import com.scoring.application.generator.ScoringGenerator;
import com.scoring.domain.ClientSummary;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ClientSummaryListener {

    @Inject
    ScoringGenerator scoringGenerator;

    @Topic("client-summary")
    public void receiveClientSummary(ClientSummary clientSummary) {
        scoringGenerator.generateScoring(clientSummary);
    }
}
