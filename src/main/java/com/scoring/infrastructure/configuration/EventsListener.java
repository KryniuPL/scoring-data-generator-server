package com.scoring.infrastructure.configuration;

import com.scoring.infrastructure.KafkaDatabaseUtils;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.ShutdownEvent;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;

@Singleton
@Slf4j
public class EventsListener {

    @Inject
    AdminClient adminClient;

    @Inject
    KafkaDatabaseUtils kafkaDatabaseUtils;

    @EventListener
    public void onStartupEvent(StartupEvent event) {
        adminClient.createTopics(List.of(
                new NewTopic("clients", 1, (short) 1),
                new NewTopic("accounts", 1, (short) 1),
                new NewTopic("payments-history", 1, (short) 1),
                new NewTopic("payments-history", 1, (short) 1)
        ));
        kafkaDatabaseUtils.createPaymentsStream();
    }

    @EventListener
    @Requires(property = "application.events.enabled", value = "true")
    public void onShutdownEvent(ShutdownEvent event) {
        log.info("Closing application... Deleting all kafka topics and ksqldb state");
        adminClient.deleteTopics(List.of("clients", "client-summary", "accounts", "payments-history"));
        kafkaDatabaseUtils.deleteAllTablesAndStreams();
    }
}
