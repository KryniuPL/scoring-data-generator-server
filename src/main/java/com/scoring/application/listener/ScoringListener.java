package com.scoring.application.listener;

import com.scoring.domain.Scoring;
import com.scoring.domain.ScoringCalculatedEvent;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.event.ApplicationEventPublisher;
import jakarta.inject.Inject;

import java.time.Clock;
import java.time.ZonedDateTime;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ScoringListener {

    @Inject
    ApplicationEventPublisher<ScoringCalculatedEvent> eventPublisher;

    @Inject
    private Clock clock;

    @Topic("scoring")
    public void receivePaymentHistory(Scoring scoring) {
        eventPublisher.publishEvent(ScoringCalculatedEvent.fromScoring(scoring, ZonedDateTime.now(clock)));
    }
}
