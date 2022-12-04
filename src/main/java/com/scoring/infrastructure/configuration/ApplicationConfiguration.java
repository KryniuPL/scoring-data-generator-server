package com.scoring.infrastructure.configuration;

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
    Client kafkaDatabaseClient(KafkaDatabaseProperties kafkaDatabaseProperties) {
        ClientOptions options = ClientOptions.create()
                .setHost(kafkaDatabaseProperties.host())
                .setPort(kafkaDatabaseProperties.port());
        return Client.create(options);
    }
}
