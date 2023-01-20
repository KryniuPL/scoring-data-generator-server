package com.scoring.infrastructure.repository;

import com.scoring.application.repository.ScoringRepository;
import io.confluent.ksql.api.client.Client;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaScoringRepository implements ScoringRepository {

    private final Client client;

    @Override
    public Integer getAllCount() {
        return client.executeQuery("SELECT * FROM SCORINGS;").join().size();
    }

    @Override
    public Integer getCountOfScoresGreaterThan(Integer score) {
        return client.executeQuery(
                "SELECT * FROM SCORINGS WHERE SCORE >= %s;".formatted(score))
                .join()
                .size();
    }
}
