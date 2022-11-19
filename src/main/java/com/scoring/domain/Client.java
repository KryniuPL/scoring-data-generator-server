package com.scoring.domain;

import io.micronaut.configuration.kafka.annotation.KafkaKey;

import java.util.UUID;

public record Client(
        @KafkaKey UUID clientId,
        String firstName,
        String lastName,
        String pesel,
        ClientType clientType
) {
}
