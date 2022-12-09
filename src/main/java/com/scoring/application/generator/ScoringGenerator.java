package com.scoring.application.generator;

import com.scoring.application.producer.ScoringProducer;
import com.scoring.domain.AccountStatus;
import com.scoring.domain.ClientSummary;
import com.scoring.domain.Scoring;
import com.scoring.domain.ScoringAvailability;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

import static com.scoring.application.utils.RandomUtils.randomDouble;
import static com.scoring.application.utils.RandomUtils.randomInteger;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ScoringGenerator {

    @Inject
    ScoringProducer scoringProducer;

    @Topic("client-summary")
    public void generateClientSummaries(ClientSummary clientSummary) {
        Scoring scoring = Scoring.builder()
                .scoringId(UUID.randomUUID())
                .clientId(clientSummary.clientId())
                .score(randomInteger(50, 100))
                .probability(randomDouble(0.00, 1.00))
                .scoringAvailability(calculateScoringAvailability(clientSummary.lastStatuses()))
                .build();

        scoringProducer.sendScoring(scoring.scoringId(), scoring);
    }

    private ScoringAvailability calculateScoringAvailability(List<AccountStatus> lastStatues) {
        return lastStatues.contains(AccountStatus.VINDICATION) ?
                ScoringAvailability.NO_HISTORY : ScoringAvailability.SCORING_AVAILABLE;
    }
}
