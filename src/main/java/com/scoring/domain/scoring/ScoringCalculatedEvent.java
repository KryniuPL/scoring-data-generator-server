package com.scoring.domain.scoring;

import java.time.ZonedDateTime;

public record ScoringCalculatedEvent(Integer scoring, String dateTime) {

    public static ScoringCalculatedEvent fromScoring(Scoring scoring, ZonedDateTime dateTime) {
        return new ScoringCalculatedEvent(scoring.score(), dateTime.toString());
    }
}
