package com.scoring.infrastructure.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("kafka.database")
public record KafkaDatabaseProperties(String host, Integer port) {
}
