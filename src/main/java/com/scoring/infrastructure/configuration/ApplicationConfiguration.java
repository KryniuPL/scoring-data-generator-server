package com.scoring.infrastructure.configuration;

import com.scoring.application.repository.ScoringRepository;
import com.scoring.infrastructure.repository.KafkaScoringRepository;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

import java.time.Clock;

@Factory
public class ApplicationConfiguration {

    @Singleton
    Clock clock() {
        return Clock.systemUTC();
    }

    @Singleton
    ScoringRepository scoringRepository() {
        ClientOptions options = ClientOptions.create()
                .setHost("localhost")
                .setPort(8088);
        Client client = Client.create(options);

        client.executeStatement("""
                CREATE STREAM IF NOT EXISTS SCORINGS (scoringId VARCHAR, score INTEGER)
                WITH (kafka_topic='scoring', value_format='json', partitions=1);
        """).join();

        return new KafkaScoringRepository(client);
    }
}
