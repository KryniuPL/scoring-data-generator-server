package com.scoring.domain;

import java.util.UUID;

public record Scoring(
        Integer score,
        Integer probability,
        ScoringAvailability scoringAvailability,
        UUID clientId
) {
}


