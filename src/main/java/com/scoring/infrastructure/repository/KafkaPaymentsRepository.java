package com.scoring.infrastructure.repository;

import com.scoring.application.repository.PaymentsRepository;
import io.confluent.ksql.api.client.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class KafkaPaymentsRepository implements PaymentsRepository {

    @Inject
    private Client kafkaDatabaseClient;

    @Override
    public Long countAll() {
        return kafkaDatabaseClient.executeQuery("select * from payments_metrics;")
                .join()
                .get(0)
                .getLong("PAYMENTS_COUNT");
    }
}
