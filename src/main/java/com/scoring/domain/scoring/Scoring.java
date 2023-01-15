package com.scoring.domain.scoring;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Scoring(
        UUID scoringId,
        Integer score,
        ScoringMetadata scoringMetadata,
        ScoringAvailability scoringAvailability,
        UUID clientId
) {
}


