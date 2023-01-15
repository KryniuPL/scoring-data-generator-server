package com.scoring.application.generator;

import com.scoring.application.producer.ScoringProducer;
import com.scoring.domain.client.Client;
import com.scoring.domain.client.ClientSummary;
import com.scoring.domain.scoring.Scoring;
import com.scoring.domain.scoring.ScoringAvailability;
import com.scoring.domain.scoring.ScoringMetadata;
import jakarta.inject.Inject;

import java.util.UUID;

import static com.scoring.domain.scoring.ScoringAvailability.*;

public class ScoringGenerator {

    private static final double BORDER_OF_SCORING_AVAILABILITY = 0.5;
    private static final Integer MAX_SCORING = 556;

    @Inject
    ScoringProducer scoringProducer;
    @Inject
    ScoringCalculator scoringCalculator;

    public void generateScoring(ClientSummary clientSummary, String producerId) {
        ScoringMetadata scoringMetadata = scoringCalculator.calculateScoring(clientSummary);
        Integer score = scoringMetadata.sum();
        Client client = clientSummary.client();

        Scoring scoring = Scoring.builder()
                .scoringId(UUID.randomUUID())
                .clientId(client.clientId())
                .scoringMetadata(scoringMetadata)
                .score(score)
                .scoringAvailability(calculateScoringAvailability(score))
                .build();

        scoringProducer.sendScoring(scoring.scoringId(), scoring, producerId);
    }

    private ScoringAvailability calculateScoringAvailability(Integer score) {
        double scoreRatio = (double) score / (double) MAX_SCORING;
        return scoreRatio < BORDER_OF_SCORING_AVAILABILITY ? SCORING_UNAVAILABLE : SCORING_AVAILABLE;
    }
}
