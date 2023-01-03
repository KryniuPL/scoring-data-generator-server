package com.scoring.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Scoring(
        UUID scoringId,
        Integer score,
        ScoringAvailability scoringAvailability,
        UUID clientId
) {
}


