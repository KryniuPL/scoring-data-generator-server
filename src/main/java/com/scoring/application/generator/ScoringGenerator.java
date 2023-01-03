package com.scoring.application.generator;

import com.scoring.application.producer.ScoringProducer;
import com.scoring.domain.ClientSummary;
import com.scoring.domain.Scoring;
import com.scoring.domain.ScoringAvailability;
import jakarta.inject.Inject;

import java.util.UUID;

import static com.scoring.domain.ScoringAvailability.NO_HISTORY;
import static com.scoring.domain.ScoringAvailability.SCORING_AVAILABLE;

public class ScoringGenerator {

    private static final double BORDER_OF_SCORING_AVAILABILITY = 0.5;
    private static final Integer MAX_SCORING = 556;

    @Inject
    ScoringProducer scoringProducer;
    @Inject
    ScoringCalculator scoringCalculator;

    public void generateScoring(ClientSummary clientSummary, String producerId) {
        Integer score = scoringCalculator.calculateScoring(clientSummary);

        Scoring scoring = Scoring.builder()
                .scoringId(UUID.randomUUID())
                .clientId(clientSummary.clientId())
                .score(score)
                .scoringAvailability(calculateScoringAvailability(score))
                .build();

        scoringProducer.sendScoring(scoring.scoringId(), scoring, producerId);
    }

    private ScoringAvailability calculateScoringAvailability(Integer score) {
        double scoreRatio = (double) score / (double) MAX_SCORING;
        return scoreRatio < BORDER_OF_SCORING_AVAILABILITY ? NO_HISTORY : SCORING_AVAILABLE;
    }
}
