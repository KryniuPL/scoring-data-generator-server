package com.scoring.infrastructure.configuration;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.ShutdownEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;

import java.util.List;

@Singleton
@Slf4j
@Requires(property = "application.events.enabled", value = "true")
public class EventsListener {

    @Inject
    AdminClient adminClient;

    @EventListener
    public void onShutdownEvent(ShutdownEvent event) {
        log.info("Closing application... Deleting all kafka topics");
        adminClient.deleteTopics(List.of("clients", "accounts", "payments-history"));
    }
}
