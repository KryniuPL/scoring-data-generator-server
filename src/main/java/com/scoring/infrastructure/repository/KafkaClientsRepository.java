package com.scoring.infrastructure.repository;

import com.scoring.application.repository.ClientsRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class KafkaClientsRepository implements ClientsRepository {

    @Inject
    private io.confluent.ksql.api.client.Client kafkaDatabaseClient;

    @Override
    public List<UUID> getAllClientsUUIDS() {
        return kafkaDatabaseClient.executeQuery("select * from clients;")
                .join()
                .stream()
                .map(row -> UUID.fromString(row.getString("CLIENTID")))
                .toList();
    }
}
