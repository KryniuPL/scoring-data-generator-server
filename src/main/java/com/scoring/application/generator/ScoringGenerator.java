package com.scoring.application.generator;

import com.scoring.application.producer.ScoringProducer;
import com.scoring.domain.AccountStatus;
import com.scoring.domain.ClientSummary;
import com.scoring.domain.Scoring;
import com.scoring.domain.ScoringAvailability;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.scoring.application.utils.RandomUtils.isHigherThan;
import static com.scoring.application.utils.RandomUtils.randomDouble;

public class ScoringGenerator {

    @Inject
    ScoringProducer scoringProducer;

    public void generateScoring(ClientSummary clientSummary) {
        Integer score = calculateScoring(clientSummary);

        Scoring scoring = Scoring.builder()
                .scoringId(UUID.randomUUID())
                .clientId(clientSummary.clientId())
                .score(score)
                .probability(randomDouble(0.00, 1.00))
                .scoringAvailability(calculateScoringAvailability(score))
                .build();

        scoringProducer.sendScoring(scoring.scoringId(), scoring);
    }

    private ScoringAvailability calculateScoringAvailability(Integer score) {
        return score < 50 ? ScoringAvailability.NO_HISTORY : ScoringAvailability.SCORING_AVAILABLE;
    }

    private Integer calculateScoring(ClientSummary clientSummary) {
        AtomicReference<Integer> initialScoring = new AtomicReference<>(100);
        clientSummary.lastStatuses().forEach(accountStatus -> {
            if (accountStatus == AccountStatus.VINDICATION || accountStatus == AccountStatus.EXECUTION) {
                initialScoring.updateAndGet(v -> v - 10);
            }
        });
        if (clientSummary.maxDelayedDays() > 5) {
            initialScoring.updateAndGet(v -> v - 25);
        }
        if (isHigherThan(clientSummary.maxOverdueAmount(), BigDecimal.valueOf(50L))) {
            initialScoring.updateAndGet(v -> v - 25);
        }
        return initialScoring.get();
    }
}
