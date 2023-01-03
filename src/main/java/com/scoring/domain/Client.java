package com.scoring.domain;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import lombok.Builder;

import java.util.UUID;

@Builder
public record Client(
        @KafkaKey UUID clientId,
        ClientJob clientJob,
        ClientMartialStatus clientMartialStatus,
        String firstName,
        String lastName,
        Integer age,
        String pesel,
        ClientType clientType
) {
}
