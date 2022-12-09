package com.scoring.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Scoring(
        UUID scoringId,
        Integer score,
        Double probability,
        ScoringAvailability scoringAvailability,
        UUID clientId
) {
}


