package com.scoring.infrastructure.configuration;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

import java.time.Clock;

@Factory
public class ApplicationConfiguration {

    @Singleton
    Clock clock() {
        return Clock.systemUTC();
    }
}
