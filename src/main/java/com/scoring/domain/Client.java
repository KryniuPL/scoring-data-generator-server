package com.scoring.domain;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import lombok.Builder;

import java.util.UUID;

@Builder
public record Client(
        @KafkaKey UUID clientId,
        String firstName,
        String lastName,
        String pesel,
        ClientType clientType
) {
}
